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
<img src='./images/basic_textview_span_1.png' />
