## TextView使用Html适配文字颜色(color:"")、文字大小(font-size:14px)、文字权重(font-weight:500)

`TextView`中提供了`Html`类，专门用来方便`TextView`展示`Html`格式的内容展示，对于常见的标签都做了简单的适配。
目前`Html`中支持解析如下标签：
* br  换行
* p   文本段落标签
* ul  列表标签，一般和`li`标签一起使用
* li  列表标签，一般和`ul`标签一起使用
* div  用来区分一块区域
* span  文本标签，用于处理文本样式
* strong  文字短语标签，用来加粗文本
* b  标签定义粗体的文本。
* em  文字短语标签，用来呈现要强调的文本
* cite 标签定义作品（比如书籍、歌曲、电影、电视节目、绘画、雕塑等等）的标题
* dfn  文字短语标签，用来定义一个定义项目
* i  定义与文本中其余部分不同的部分，并把这部分文本呈现为斜体文本。
* big  标签用来制作更大的文本
* small 标签定义小型文本（和旁注）
* font  标签规定文本的字体、字体尺寸、字体颜色。
* blockquote  标签定义摘自另一个源的块引用。
* tt  标签定义打字机文本。
* a  定义超链接
* u  标签定义与常规文本风格不同的文本，像拼写错误的单词或者汉语中的专有名词。
* del  标签定义文档中已删除的文本。
* s   标签对那些不正确、不准确或者没有用的文本进行标识。
* strike  定义加删除线文本。
* sup    标签定义上标文本。上标文本将会显示在当前文本流中字符高度的一半为基准线的上方，但是与当前文本流中文字的字体和字号都是一样的。上标文本能用来添加脚注，比如 WWW[1]。
* sub   标签定义下标文本。下标文本将会显示在当前文本流中字符高度的一半为基准线的下方，但是与当前文本流中文字的字体和字号都是一样的。下标文本能用来表示化学公式，比如 H2O。
* img  展示图片，需要借助`Html.ImageGetter`才能正常展示图片

一般如果对文字进行展示，都是对需要强调的文字使用`<span>xxx</span>`进行包裹，当然也可以使用`<font>xxx</font>`。
但是前端真正使用的过程中，很少有使用`<font>`标签强调字体的。

如果我们想要展示一段文字，其中文本内容有通过`<span>`标签对文字大小和文字颜色、文字展示加粗权重
做了处理，根据上面列出来的标签，你一定会想到直接使用`Html.formHtml`进行展示了，因为支持标签中可以看到，android的`Html`
标签是对`Span`做了支持的。
```kotlin
val result = "<span>攀钢钒钛所属行业为\n" +
        "<span style=\"color:#333333; font-weight:bold; font-size:18px;\">其他采掘</span>；\n" +
        "</span>\n" +
        "<br/>\n" +
        "<span>当日攀钢钒钛行情整体表现<span style=\"color:green; font-weight:bold;\">弱于</span>所属行业表现；</span>\n" +
        "<br/>\n" +
        "<span>攀钢钒钛所属概念中<span style=\"color:#333333; font-weight:bold; \">有色金属</span>表现相对优异；</span>\n" +
        "<br/>\n" +
        "<span>其涨跌幅在有色金属中位列<span style=\"color:#F43737; font-weight:bold; \">81</span>/<span style=\"color:black; font-weight:bold; \">122</span>。</span>"
tvTest.text = Html.fromHtml(result)
```
可是，结果真的会是我们想象的那样吗？

<img src='https://img-blog.csdnimg.cn/20210205163651978.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70' />

可以从上面的图看出来，文字的颜色生效了，但是文字的大小设置和权重设置都没有任何的效果。那么，android的`Html`处理真的是支持完整的属性支持吗？这个时候，就需要
呆着这个疑问看源代码了。

```
public static Spanned fromHtml(String source, int flags, ImageGetter imageGetter,
            TagHandler tagHandler) {
    ...
    HtmlToSpannedConverter converter =
            new HtmlToSpannedConverter(source, imageGetter, tagHandler, parser, flags);
    return converter.convert();
}
```
可以看到实际的对于标签的处理操作，是使用`HtmlToSpannedConverter#convert`方法进行的处理。
```
public Spanned convert() {
    mReader.setContentHandler(this);
    try {
        mReader.parse(new InputSource(new StringReader(mSource)));
    } catch (IOException e) {
        // We are reading from a string. There should not be IO problems.
        throw new RuntimeException(e);
    } catch (SAXException e) {
        // TagSoup doesn't throw parse exceptions.
        throw new RuntimeException(e);
    }
    ...
    return mSpannableStringBuilder;
}
```
通过`mReader.setContentHandler(this);`和`mReader.parse(new InputSource(new StringReader(mSource)));`
可以看到这块代码其实是使用`Sax`解析方式，对`html`进行解析（html其实可以理解为一种特殊的xml）。知道是`Sax`解析之后，我们
只需要关心解析的过程中`span`是否有对`font-size`和`font-weight`进行识别。
```
private void startCssStyle(Editable text, Attributes attributes) {
    String style = attributes.getValue("", "style");
    if (style != null) {
        Matcher m = getForegroundColorPattern().matcher(style);
        if (m.find()) {
            int c = getHtmlColor(m.group(1));
            if (c != -1) {
                start(text, new Foreground(c | 0xFF000000));
            }
        }
        m = getBackgroundColorPattern().matcher(style);
        if (m.find()) {
            int c = getHtmlColor(m.group(1));
            if (c != -1) {
                start(text, new Background(c | 0xFF000000));
            }
        }
        m = getTextDecorationPattern().matcher(style);
        if (m.find()) {
            String textDecoration = m.group(1);
            if (textDecoration.equalsIgnoreCase("line-through")) {
                start(text, new Strikethrough());
            }
        }
    }
}
```
可以看到首先获取了`span`标签的`style`属性，然后使用`getForegroundColorPattern`和`getBackgroundColorPattern`分别解析出来了前景色和背景色。
并通过
```
private static void start(Editable text, Object mark) {
    int len = text.length();
    text.setSpan(mark, len, len, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
}
```
`Span`的方式设置到代码中。但是发现代码中并没有对`font-size`和`font-weight`进行处理。

问题至此找到了，通过对这段代码的分析，也能发现系统进行`Html`标签支持的套路，那么剩下来的就是照🐱 画🐯的操作了。

`Html`提供了一个接口`Html.TagHandler`用来方便我们实现自己的标签
```
public class SpanExtTagHandler implements Html.TagHandler {
    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {
        ...
    }
}
```
可以在`handleTag`中设置很多事情，那么直接上代码。
```
public class SpanExtTagHandler implements Html.TagHandler {

    private final String TAG = "CustomTagHandler";

    private int startIndex = 0;
    private int stopIndex = 0;

    private ColorStateList mOriginColors;
    private Context mContext;

    public SpanExtTagHandler(Context context, ColorStateList originColors) {
        mContext = context;
        mOriginColors = originColors;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {
        processAttributes(xmlReader);

        if (tag.equalsIgnoreCase("spanExt")) {
            if (opening) {
                startSpan(tag, output, xmlReader);
            } else {
                endSpan(tag, output, xmlReader);
                attributes.clear();
            }
        }

    }

    public void startSpan(String tag, Editable output, XMLReader xmlReader) {
        startIndex = output.length();
    }

    public void endSpan(String tag, Editable output, XMLReader xmlReader) {
        stopIndex = output.length();

        String color = attributes.get("color");
        String size = attributes.get("size");
        String style = attributes.get("style");
        if (!TextUtils.isEmpty(style)) {
            analysisStyle(startIndex, stopIndex, output, style);
        }
        if (!TextUtils.isEmpty(size)) {
            size = size.split("px")[0];
        }
        if (!TextUtils.isEmpty(color)) {
            if (color.startsWith("@")) {
                Resources res = Resources.getSystem();
                String name = color.substring(1);
                int colorRes = res.getIdentifier(name, "color", "android");
                if (colorRes != 0) {
                    output.setSpan(new ForegroundColorSpan(colorRes), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } else {
                try {
                    output.setSpan(new ForegroundColorSpan(Color.parseColor(color)), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                    reductionFontColor(startIndex, stopIndex, output);
                }
            }
        }
        if (!TextUtils.isEmpty(size)) {
            int fontSizePx = 16;
            if (null != mContext) {
                fontSizePx = DisplayUtil.sp2px(mContext, Integer.parseInt(size));
            }
            output.setSpan(new AbsoluteSizeSpan(fontSizePx), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    final HashMap<String, String> attributes = new HashMap<String, String>();

    private void processAttributes(final XMLReader xmlReader) {
        try {
            Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
            elementField.setAccessible(true);
            Object element = elementField.get(xmlReader);
            Field attsField = element.getClass().getDeclaredField("theAtts");
            attsField.setAccessible(true);
            Object atts = attsField.get(element);
            Field dataField = atts.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            String[] data = (String[]) dataField.get(atts);
            Field lengthField = atts.getClass().getDeclaredField("length");
            lengthField.setAccessible(true);
            int len = (Integer) lengthField.get(atts);
            for (int i = 0; i < len; i++)
                attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 还原为原来的颜色
     */
    private void reductionFontColor(int startIndex, int stopIndex, Editable editable) {
        if (null != mOriginColors) {
            editable.setSpan(new TextAppearanceSpan(null, 0, 0, mOriginColors, null),
                    startIndex, stopIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            editable.setSpan(new ForegroundColorSpan(0xff2b2b2b), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 解析style属性
     */
    private void analysisStyle(int startIndex, int stopIndex, Editable editable, String style) {
        Log.e(TAG, "style：" + style);
        String[] attrArray = style.split(";");
        Map<String, String> attrMap = new HashMap<>();
        if (null != attrArray) {
            for (String attr : attrArray) {
                String[] keyValueArray = attr.split(":");
                if (null != keyValueArray && keyValueArray.length == 2) {
                    // 记住要去除前后空格
                    attrMap.put(keyValueArray[0].trim(), keyValueArray[1].trim());
                }
            }
        }
        Log.e(TAG, "attrMap：" + attrMap.toString());

        String color = attrMap.get("color");
        String fontSize = attrMap.get("font-size");
        String fontWeight = attrMap.get("font-weight");
        if (!TextUtils.isEmpty(fontWeight) && "bold".equalsIgnoreCase(fontWeight)) {
            editable.setSpan(new StyleSpan(Typeface.BOLD), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (!TextUtils.isEmpty(fontWeight) && "italic".equalsIgnoreCase(fontWeight)) {
            editable.setSpan(new StyleSpan(Typeface.ITALIC), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!TextUtils.isEmpty(fontSize)) {
            fontSize = fontSize.split("px")[0];
        }
        if (!TextUtils.isEmpty(color)) {
            if (color.startsWith("@")) {
                Resources res = Resources.getSystem();
                String name = color.substring(1);
                int colorRes = res.getIdentifier(name, "color", "android");
                if (colorRes != 0) {
                    editable.setSpan(new ForegroundColorSpan(colorRes), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } else {
                try {
                    editable.setSpan(new ForegroundColorSpan(Color.parseColor(color)), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                    reductionFontColor(startIndex, stopIndex, editable);
                }
            }
        }
        if (!TextUtils.isEmpty(fontSize)) {
            int fontSizePx = 16;
            if (null != mContext) {
                fontSizePx = DisplayUtil.sp2px(mContext, Integer.parseInt(fontSize));
            }
            editable.setSpan(new AbsoluteSizeSpan(fontSizePx), startIndex, stopIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

}  
```
由于系统默认实现已经存在了`span`标签，所以我们自己设置的标签如果也是`span`的话，是不能生效的，这里我使用了自定义标签`spanExt`。

接下来就可以看一下使用的效果了。
```
val result = "<spanExt>攀钢钒钛所属行业为\n" +
        "<spanExt style=\"color:#333333; font-weight:bold; font-size:18px;\">其他采掘</spanExt>；\n" +
        "</spanExt>\n" +
        "<br/>\n" +
        "<spanExt>当日攀钢钒钛行情整体表现<spanExt style=\"color:green; font-weight:bold;\">弱于</spanExt>所属行业表现；</spanExt>\n" +
        "<br/>\n" +
        "<spanExt>攀钢钒钛所属概念中<spanExt style=\"color:#333333; font-weight:bold; \">有色金属</spanExt>表现相对优异；</spanExt>\n" +
        "<br/>\n" +
        "<spanExt>其涨跌幅在有色金属中位列<spanExt style=\"color:#F43737; font-weight:bold; \">81</spanExt>/<spanExt style=\"color:black; font-weight:bold; \">122</spanExt>。</spanExt>"
tvTest.text =
    Html.fromHtml(
        result, null,
        SpanExtTagHandler(
            this@TextViewModuleActivity,
            null
        )
    )
```
效果如下：
<img src="https://img-blog.csdnimg.cn/20210205163703386.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70" />

感谢您的阅读!!!🙏🙏🙏

