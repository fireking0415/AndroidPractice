## TextView源码分析

`TextView` 应该是作为`Android`开发最先熟悉的Weight组建了，可以用来集合`Html`和`Spannable`进行展示文字、展示html、进行高亮处理，还能通过`autolink`进行`email`、
`tel`等功能的识别跳转。

那么你真的对`TextView`熟悉吗？我准备了几个问题，你可以对照自己看看你能回答出来几个？

* 1、为什么使用了`android:includeFontPadding="false"`之后可以去除文字的上下边距？到底去除的是那块内容？
* 2、为什么`android:ellipsize="end"`必须要在设置了单行文本(`android:maxLines="1"`或者`android:singleLine="true"`)的情况下才能生效？
* 3、`TextView`是怎么支持`Spannable`和`Html`方式内容展示的？
* 4、`TextView`是怎么实现文字的换行的？怎么实现单词不打断拆分的？
* 5、为什么`TextView`可以实现文字的换行，但是我们自己使用`canvas.drawText`只能绘制单行？
* 6、为什么`EditText`不支持`android:ellipsize="end"`和`android:ellipsize="marquee"`属性？如果想要让`EditText`实现单行省略号暂时，有哪些实现方式？

我比较喜欢带着问题来分析代码，那么接下来进入主题，源码分析走起。

```
public class TextView extends View implements ViewTreeObserver.OnPreDrawListener {
    ...
}
```
TextView是一个自定义的View对象，那么根据传统的自定义View三大套，`onMeasure`、`onLayout`、`onDraw`来依次分析。

### 1、量体裁衣之onMeasure

### 2、排兵布阵之onLayout

### 3、跃然纸上之onDraw
