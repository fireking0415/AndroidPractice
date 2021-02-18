
在ui开发开发中，label文本标签是最长用到的。android中提供了`TextView`帮我们用来展示文本文字。而android的`Canvas`也提供了`drawText`用于帮助我们在自定义view的时候绘制展示文本。

先简单看一下这段代码
```kotlin
private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

init {
    paint.textSize = 150F
    paint.style = Paint.Style.FILL_AND_STROKE
    paint.color = Color.parseColor("#FFFFFF")
}

override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.drawColor(Color.parseColor("#80F14400"))
    val textLabel = "Hello World, Hello Python"
    canvas?.drawText(textLabel, 0F, 0F, paint)
}
```
上面这段代码，是一个最简单的`drawText`使用。按照我们对android坐标系的理解，貌似没什么问题，应该是在view的左上角绘制文本，文本完整展示。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210218134254601.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70)
从真实的展示上效果来看，和我们想要的效果完全不一致，为什么会出现这种情况呢？还是看一下`drawText`的方法定义吧。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021021813442867.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70)
从方法的注释中可以得知`drawText`的`x`和`y`分别是用来定义文字绘制的起始点，而这个起始点的`y`坐标是基于`baseline`(基线)。想要理解`baseline`的概念，就要理解文字的测量规则，`canvas`文字测量规则是使用`FontMetrics`方法中定义的。而`FontMetrics`是从`Paint.getFontMetrics()`获取。

在看`FontMetrics`定义之前，先看一下这张图，这张图是从别人博客copy过来的（对于图片中的leading值，图中理解不是很正确，暂时忽略）
>leading 指的是行的额外间距，即对于上下相邻的两行，上行的 bottom 线和下行的 top 线的距离

![在这里插入图片描述](https://img-blog.csdnimg.cn/2021021813581651.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70)

`FontMetics`类主要是定义了文字的测量属性。
```java
public static class FontMetrics {
    /**
     * The maximum distance above the baseline for the tallest glyph in
     * the font at a given text size.
     * 在给定的文本大小下，字体中最高字形的基线上方的最大距离。
     */
    public float   top;
    /**
     * The recommended distance above the baseline for singled spaced text.
     * 对于单行分隔的文本，建议的基线上方距离。
     */
    public float   ascent;
    /**
     * The recommended distance below the baseline for singled spaced text.
     * 推荐的基线以下距离，用于分隔的文本。
     */
    public float   descent;
    /**
     * The maximum distance below the baseline for the lowest glyph in
     * the font at a given text size.
     * 在给定的文本大小下，字体下方最低字形的基线以下最大距离。
     */
    public float   bottom;
    /**
     * The recommended additional space to add between lines of text.
     * 建议在文本行之间添加额外的空格。
     */
    public float   leading;
}
```
从上面的`FontMetrics`定义中以及`drawText`中可以发现，都是使用`baseline`作为一个基准线来确定距离。

可以看出来`baseline`的确认很重要，那么接下来看一下`baseline`怎么计算。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210218142105275.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70)
从图中可以看出来，`baseline`的大小`y`值等于`fontMetrics.bottom + fontMetrics.top -fontMetrics.descent `。那么，可以打印一下`FontMetrics`的各个属性的值，看一下输出。
```kotlin
val fontMetrics = paint.fontMetrics
Log.e("info", "fontMetrics-->top: " + fontMetrics.top)
Log.e("info", "fontMetrics-->leading: " + fontMetrics.leading)
Log.e("info", "fontMetrics-->ascent: " + fontMetrics.ascent)
Log.e("info", "fontMetrics-->descent: " + fontMetrics.descent)
Log.e("info", "fontMetrics-->bottom: " + fontMetrics.bottom)
```
E/info: fontMetrics-->top: -157.2
E/info: fontMetrics-->leading: 0.0
E/info: fontMetrics-->ascent: -139.2
E/info: fontMetrics-->descent: 36.600002
E/info: fontMetrics-->bottom: 40.65

将打印的结果对照图，会发现，起始数据基于`baseline`，在baseline上方的为负数、下方的为正数。

知道了怎么每个值的意义和`baseline`的计算方式之后，我们就可以根据`baseline`作为绘制出来每个属性的辅助线和文字

```kotlin
val fontMetrics = paint.fontMetrics
val baseline = fontMetrics.bottom + kotlin.math.abs(fontMetrics.top) - fontMetrics.descent
canvas?.save()
//将坐标零点y值移动到baseline
canvas?.translate(
    0F,
    baseline
)
paint.color = Color.BLUE
val textLabel = "Hello,Python"
canvas?.drawText(textLabel, 0F, 0F, paint)
paint.style = Paint.Style.STROKE
//绘制top
paint.color = Color.BLUE
canvas?.drawLine(0F, fontMetrics.top, width.toFloat(), fontMetrics.top, paint)
paint.color = Color.YELLOW
//绘制ascent
canvas?.drawLine(0F, fontMetrics.ascent, width.toFloat(), fontMetrics.ascent, paint)
paint.color = Color.GRAY
//绘制descent
canvas?.drawLine(0F, fontMetrics.descent, width.toFloat(), fontMetrics.descent, paint)
paint.color = Color.parseColor("#F15500")
//绘制bottom
canvas?.drawLine(0F, fontMetrics.bottom, width.toFloat(), fontMetrics.bottom, paint)
//绘制baseline
paint.color = Color.parseColor("#8080FF")
paint.pathEffect = DashPathEffect(floatArrayOf(10F, 10F), 0F)
canvas?.drawLine(0F, 0F, width.toFloat(), 0F, paint)
canvas?.restore()
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210218151952140.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70)



