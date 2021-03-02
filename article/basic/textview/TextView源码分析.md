TextView是android提供的一个文本展示ui控件，同时也是android开发者最先熟悉的Weight组件，可以配合Html和Spannable进行展示文字、展示html、进行高亮处理，还能通过autolink进行email、tel等功能的识别跳转，本篇文章将带你从系统源码的角度彻底搞定TextView的绘制流程。

TextView的依赖关系
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210223170212253.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70)
TextView本身是一个自定义View控件，所以对于Textview的分析，可以直接按照常用的自定义View绘制流程来分析。

* onMeasure
* onLayout
* onDraw

### onMeasure
在onMeasure中，按照常规对于自定义View的流程，我们主要是确定控件本身的宽、高是如何确定出来的。

```java
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int widthMode = MeasureSpec.getMode(widthMeasureSpec);
       int heightMode = MeasureSpec.getMode(heightMeasureSpec);
       int widthSize = MeasureSpec.getSize(widthMeasureSpec);
       int heightSize = MeasureSpec.getSize(heightMeasureSpec);

       int width;
       int height;

		//BoringLayout.Metrics UNKNOWN_BORING = new BoringLayout.Metrics();
		// UNKNOWN_BORING 是一个metrics对象，Metrics对象主要是用来确定文字的绘制。
		//对于Metrics可以参考文章: https://blog.csdn.net/wanggang514260663/article/details/113845402
       BoringLayout.Metrics boring = UNKNOWN_BORING;
       BoringLayout.Metrics hintBoring = UNKNOWN_BORING;
		
		//返回文字的对齐方式，比如LTR和RTL
       if (mTextDir == null) {
           mTextDir = getTextDirectionHeuristic();
       }
		
       int des = -1;
       boolean fromexisting = false;
       final float widthLimit = (widthMode == MeasureSpec.AT_MOST)
               ?  (float) widthSize : Float.MAX_VALUE;
		// 如果是使用确定值的大小测量方式，则使用测量的确定值
       if (widthMode == MeasureSpec.EXACTLY) {
           width = widthSize;
       } else {
           if (mLayout != null && mEllipsize == null) {
               //如果文字行数>1，则返回-1，否则返回该行文字长度
               des = desired(mLayout);
           }
           // 如果行数>1
           if (des < 0) {
               //BoringLayout是Layout的最简单的实现，主要用于适配单行文字展示，
               //并且只支持从左到右的展示方向。不建议在自己的开发过程中直接使用，
               //如果需要使用的话，首先使用isBoring判断文字是否符合要求。
               boring = BoringLayout.isBoring(mTransformed, mTextPaint, mTextDir, mBoring);
               //BoringLayout.isBoring判断如果负责BoringLayout要求，则返回文测量结果Metrics对象，
               //否则返回null
               if (boring != null) {
                   mBoring = boring;
               }
           } else {
               fromexisting = true;
           }
           //boring == null表示行数==0 并且不支持boringLayout方式
           if (boring == null || boring == UNKNOWN_BORING) {
              //des < 0，则表示文字有多行
               if (des < 0) {
                   des = (int) Math.ceil(Layout.getDesiredWidthWithLimit(mTransformed, 0,
                           mTransformed.length(), mTextPaint, mTextDir, widthLimit));
               }
               width = des;
           } else {
               //测量的文字的宽度
               width = boring.width;
           }
			
           final Drawables dr = mDrawables;
           if (dr != null) {
               width = Math.max(width, dr.mDrawableWidthTop);
               width = Math.max(width, dr.mDrawableWidthBottom);
           }

           if (mHint != null) {
               int hintDes = -1;
               int hintWidth;

               if (mHintLayout != null && mEllipsize == null) {
                   hintDes = desired(mHintLayout);
               }

               if (hintDes < 0) {
                   hintBoring = BoringLayout.isBoring(mHint, mTextPaint, mTextDir, mHintBoring);
                   if (hintBoring != null) {
                       mHintBoring = hintBoring;
                   }
               }

               if (hintBoring == null || hintBoring == UNKNOWN_BORING) {
                   if (hintDes < 0) {
                       hintDes = (int) Math.ceil(Layout.getDesiredWidthWithLimit(mHint, 0,
                               mHint.length(), mTextPaint, mTextDir, widthLimit));
                   }
                   hintWidth = hintDes;
               } else {
                   hintWidth = hintBoring.width;
               }

               if (hintWidth > width) {
                   width = hintWidth;
               }
           }
			
			//宽度需要加上内间距
           width += getCompoundPaddingLeft() + getCompoundPaddingRight();
			
           if (mMaxWidthMode == EMS) {
               width = Math.min(width, mMaxWidth * getLineHeight());
           } else {
               width = Math.min(width, mMaxWidth);
           }

           if (mMinWidthMode == EMS) {
               width = Math.max(width, mMinWidth * getLineHeight());
           } else {
               width = Math.max(width, mMinWidth);
           }

           // Check against our minimum width
           width = Math.max(width, getSuggestedMinimumWidth());

           if (widthMode == MeasureSpec.AT_MOST) {
               width = Math.min(widthSize, width);
           }
       }
		// 文字的真实占用宽度，不包含padding值
       int want = width - getCompoundPaddingLeft() - getCompoundPaddingRight();
       int unpaddedWidth = want;
	   //如果支持滚动，则文字宽度设置为 VERY_WIDE = 1024 * 1024;
       if (mHorizontallyScrolling) want = VERY_WIDE;
       int hintWant = want;
       int hintWidth = (mHintLayout == null) ? hintWant : mHintLayout.getWidth();
       if (mLayout == null) {
          //如果Layout对象为null，则使用makeNewLayout构造出来一个Layout对象
           makeNewLayout(want, hintWant, boring, hintBoring,
                         width - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
       } else {
           final boolean layoutChanged = (mLayout.getWidth() != want) || (hintWidth != hintWant)
                   || (mLayout.getEllipsizedWidth()
                           != width - getCompoundPaddingLeft() - getCompoundPaddingRight());

           final boolean widthChanged = (mHint == null) && (mEllipsize == null)
                   && (want > mLayout.getWidth())
                   && (mLayout instanceof BoringLayout
                           || (fromexisting && des >= 0 && des <= want));

           final boolean maximumChanged = (mMaxMode != mOldMaxMode) || (mMaximum != mOldMaximum);
			//如果文字发生了变化
           if (layoutChanged || maximumChanged) {
               if (!maximumChanged && widthChanged) {
                   //将Layout的宽度设置为期待的宽度want值
                   mLayout.increaseWidthTo(want);
               } else {
                   //重新计算构建Layout
                   makeNewLayout(want, hintWant, boring, hintBoring,
                           width - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
               }
           } else {
               // Nothing has changed
           }
       }  
       if (heightMode == MeasureSpec.EXACTLY) {
           // Parent has told us how big to be. So be it.
           // 如果测量方式为固定值方式，则使用测量出来的值
           height = heightSize;
           mDesiredHeightAtMeasure = -1;
       } else {
           //getDesiredHeight方法会根据text和hint计算出来一个最大的高度
           int desired = getDesiredHeight();
           height = desired;
           mDesiredHeightAtMeasure = desired;
			//如果是at_most方式，则取测量值和desired的相对小的数值
           if (heightMode == MeasureSpec.AT_MOST) {
               height = Math.min(desired, heightSize);
           }
       }
		//返回不包含padding值的高度
       int unpaddedHeight = height - getCompoundPaddingTop() - getCompoundPaddingBottom();
       //如果多行
       if (mMaxMode == LINES && mLayout.getLineCount() > mMaximum) {
          //mLayout.getLineTop的值是返回指定行到顶部的高度，也就是对应的指定行的高度
           unpaddedHeight = Math.min(unpaddedHeight, mLayout.getLineTop(mMaximum));
       }

       /*
        * We didn't let makeNewLayout() register to bring the cursor into view,
        * so do it here if there is any possibility that it is needed.
        */
       if (mMovement != null
               || mLayout.getWidth() > unpaddedWidth
               || mLayout.getHeight() > unpaddedHeight) {
           registerForPreDraw();
       } else {
           scrollTo(0, 0);
       }
       setMeasuredDimension(width, height);
   }
  ```
* TextView#desired
```java
private static int desired(Layout layout) {
    int n= layout.getLineCount();
    CharSequence text = layout.getText();
    float max = 0;

    // if any line was wrapped, we can't use it.
    // but it's ok for the last line not to have a newline
	//如果行数>1，则返回-1
    for (int i = 0; i < n - 1; i++) {
        //判断是否每一行的最后为\n换行
        if (text.charAt(layout.getLineEnd(i) - 1) != '\n') {
            return -1;
        }
    }
    for (int i = 0; i < n; i++) {
        //判断如果行宽度如果>0，则返回，否则返回0
        max = Math.max(max, layout.getLineWidth(i));
    }
    return (int) Math.ceil(max);
}
```
* TextView#makeNewLayout
```java
public void makeNewLayout(int wantWidth, int hintWidth,
                                 BoringLayout.Metrics boring,
                                 BoringLayout.Metrics hintBoring,
                                 int ellipsisWidth, boolean bringIntoView) {
    //停止掉跑马灯效果                             
    stopMarquee();

    // Update "old" cached values
    mOldMaximum = mMaximum;
    mOldMaxMode = mMaxMode;

    mHighlightPathBogus = true;

    if (wantWidth < 0) {
        wantWidth = 0;
    }
    if (hintWidth < 0) {
        hintWidth = 0;
    }

    Layout.Alignment alignment = getLayoutAlignment();
    final boolean testDirChange = mSingleLine && mLayout != null
            && (alignment == Layout.Alignment.ALIGN_NORMAL
                    || alignment == Layout.Alignment.ALIGN_OPPOSITE);
    int oldDir = 0;
    if (testDirChange) oldDir = mLayout.getParagraphDirection(0);
    //判断是否支持展示身略号...
    boolean shouldEllipsize = mEllipsize != null && getKeyListener() == null;
    //判断是否展示跑马灯效果
    final boolean switchEllipsize = mEllipsize == TruncateAt.MARQUEE
            && mMarqueeFadeMode != MARQUEE_FADE_NORMAL;
    TruncateAt effectiveEllipsize = mEllipsize;
    if (mEllipsize == TruncateAt.MARQUEE
            && mMarqueeFadeMode == MARQUEE_FADE_SWITCH_SHOW_ELLIPSIS) {
        //省略号展示..
        effectiveEllipsize = TruncateAt.END_SMALL;
    }
	
	//获取文字段落方向
    if (mTextDir == null) {
        mTextDir = getTextDirectionHeuristic();
    }
	//获取layout
    mLayout = makeSingleLayout(wantWidth, boring, ellipsisWidth, alignment, shouldEllipsize,
            effectiveEllipsize, effectiveEllipsize == mEllipsize);
    if (switchEllipsize) {
        TruncateAt oppositeEllipsize = effectiveEllipsize == TruncateAt.MARQUEE
                ? TruncateAt.END : TruncateAt.MARQUEE;
        //对于跑马灯效果，单独保存一个跑马灯模式的Layout
        mSavedMarqueeModeLayout = makeSingleLayout(wantWidth, boring, ellipsisWidth, alignment,
                shouldEllipsize, oppositeEllipsize, effectiveEllipsize != mEllipsize);
    }

    shouldEllipsize = mEllipsize != null;
    mHintLayout = null;
	//准备计算Hint使用的Layout
    if (mHint != null) {
        if (shouldEllipsize) hintWidth = wantWidth;

        if (hintBoring == UNKNOWN_BORING) {
            hintBoring = BoringLayout.isBoring(mHint, mTextPaint, mTextDir,
                                               mHintBoring);
            if (hintBoring != null) {
                mHintBoring = hintBoring;
            }
        }

        if (hintBoring != null) {
            if (hintBoring.width <= hintWidth
                    && (!shouldEllipsize || hintBoring.width <= ellipsisWidth)) {
                if (mSavedHintLayout != null) {
                    mHintLayout = mSavedHintLayout.replaceOrMake(mHint, mTextPaint,
                            hintWidth, alignment, mSpacingMult, mSpacingAdd,
                            hintBoring, mIncludePad);
                } else {
                    mHintLayout = BoringLayout.make(mHint, mTextPaint,
                            hintWidth, alignment, mSpacingMult, mSpacingAdd,
                            hintBoring, mIncludePad);
                }

                mSavedHintLayout = (BoringLayout) mHintLayout;
            } else if (shouldEllipsize && hintBoring.width <= hintWidth) {
                if (mSavedHintLayout != null) {
                    mHintLayout = mSavedHintLayout.replaceOrMake(mHint, mTextPaint,
                            hintWidth, alignment, mSpacingMult, mSpacingAdd,
                            hintBoring, mIncludePad, mEllipsize,
                            ellipsisWidth);
                } else {
                    mHintLayout = BoringLayout.make(mHint, mTextPaint,
                            hintWidth, alignment, mSpacingMult, mSpacingAdd,
                            hintBoring, mIncludePad, mEllipsize,
                            ellipsisWidth);
                }
            }
        }
        // TODO: code duplication with makeSingleLayout()
        if (mHintLayout == null) {
            StaticLayout.Builder builder = StaticLayout.Builder.obtain(mHint, 0,
                    mHint.length(), mTextPaint, hintWidth)
                    .setAlignment(alignment)
                    .setTextDirection(mTextDir)
                    .setLineSpacing(mSpacingAdd, mSpacingMult)
                    .setIncludePad(mIncludePad)
                    .setUseLineSpacingFromFallbacks(mUseFallbackLineSpacing)
                    .setBreakStrategy(mBreakStrategy)
                    .setHyphenationFrequency(mHyphenationFrequency)
                    .setJustificationMode(mJustificationMode)
                    .setMaxLines(mMaxMode == LINES ? mMaximum : Integer.MAX_VALUE);
            if (shouldEllipsize) {
                builder.setEllipsize(mEllipsize)
                        .setEllipsizedWidth(ellipsisWidth);
            }
            mHintLayout = builder.build();
        }
    }

    if (bringIntoView || (testDirChange && oldDir != mLayout.getParagraphDirection(0))) {
        registerForPreDraw();
    }

    if (mEllipsize == TextUtils.TruncateAt.MARQUEE) {
        if (!compressText(ellipsisWidth)) {
            final int height = mLayoutParams.height;
            // If the size of the view does not depend on the size of the text, try to
            // start the marquee immediately
            if (height != LayoutParams.WRAP_CONTENT && height != LayoutParams.MATCH_PARENT) {
                startMarquee();
            } else {
                // Defer the start of the marquee until we know our width (see setFrame())
                mRestartMarquee = true;
            }
        }
    }

    // CursorControllers need a non-null mLayout
    if (mEditor != null) mEditor.prepareCursorControllers();
}
```
* TextView#makeSingleLayout
  这个方法
```java
protected Layout makeSingleLayout(int wantWidth, BoringLayout.Metrics boring, int ellipsisWidth,
            Layout.Alignment alignment, boolean shouldEllipsize, TruncateAt effectiveEllipsize,
            boolean useSaved) {
    Layoutresult = null;
    //userDynamicLayout = isTextSelectable() || (mSpannable != null && mPrecomputed == null);
    //如果满足上面的条件，则使用DynamicLayout
    if (useDynamicLayout()) {
        final DynamicLayout.Builder builder = DynamicLayout.Builder.obtain(mText, mTextPaint,
                wantWidth)
                .setDisplayText(mTransformed)
                .setAlignment(alignment)
                .setTextDirection(mTextDir)
                .setLineSpacing(mSpacingAdd, mSpacingMult)
                .setIncludePad(mIncludePad)
                .setUseLineSpacingFromFallbacks(mUseFallbackLineSpacing)
                .setBreakStrategy(mBreakStrategy)
                .setHyphenationFrequency(mHyphenationFrequency)
                .setJustificationMode(mJustificationMode)
                .setEllipsize(getKeyListener() == null ? effectiveEllipsize : null)
                .setEllipsizedWidth(ellipsisWidth);
        result = builder.build();
    } else {
        if (boring == UNKNOWN_BORING) {
            //判断是否使用BoringLayout
            boring = BoringLayout.isBoring(mTransformed, mTextPaint, mTextDir, mBoring);
            if (boring != null) {
                mBoring = boring;
            }
        }
		//bording != null 则表示使用Boringlayout 	
        if (boring != null) {
            if (boring.width <= wantWidth
                    && (effectiveEllipsize == null || boring.width <= ellipsisWidth)) {
                if (useSaved && mSavedLayout != null) {
                    result = mSavedLayout.replaceOrMake(mTransformed, mTextPaint,
                            wantWidth, alignment, mSpacingMult, mSpacingAdd,
                            boring, mIncludePad);
                } else {
                    result = BoringLayout.make(mTransformed, mTextPaint,
                            wantWidth, alignment, mSpacingMult, mSpacingAdd,
                            boring, mIncludePad);
                }

                if (useSaved) {
                    mSavedLayout = (BoringLayout) result;
                }
            } else if (shouldEllipsize && boring.width <= wantWidth) {
                if (useSaved && mSavedLayout != null) {
                    result = mSavedLayout.replaceOrMake(mTransformed, mTextPaint,
                            wantWidth, alignment, mSpacingMult, mSpacingAdd,
                            boring, mIncludePad, effectiveEllipsize,
                            ellipsisWidth);
                } else {
                    result = BoringLayout.make(mTransformed, mTextPaint,
                            wantWidth, alignment, mSpacingMult, mSpacingAdd,
                            boring, mIncludePad, effectiveEllipsize,
                            ellipsisWidth);
                }
            }
        }
    }
    //如果不满足BoringLayout 并且不满足 DynamicLayout 则使用StaticLayout
    if (result == null) {
        StaticLayout.Builder builder = StaticLayout.Builder.obtain(mTransformed,
                0, mTransformed.length(), mTextPaint, wantWidth)
                .setAlignment(alignment)
                .setTextDirection(mTextDir)
                .setLineSpacing(mSpacingAdd, mSpacingMult)
                .setIncludePad(mIncludePad)
                .setUseLineSpacingFromFallbacks(mUseFallbackLineSpacing)
                .setBreakStrategy(mBreakStrategy)
                .setHyphenationFrequency(mHyphenationFrequency)
                .setJustificationMode(mJustificationMode)
                .setMaxLines(mMaxMode == LINES ? mMaximum : Integer.MAX_VALUE);
        if (shouldEllipsize) {
            builder.setEllipsize(effectiveEllipsize)
                    .setEllipsizedWidth(ellipsisWidth);
        }
        result = builder.build();
    }
    return result;
}
```

### onLayout
```java
protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
 super.onLayout(changed, left, top, right, bottom);
    if (mDeferScroll >= 0) {
        int curs = mDeferScroll;
        mDeferScroll = -1;
        bringPointIntoView(Math.min(curs, mText.length()));
    }
    // Call auto-size after the width and height have been calculated.
    //这里会判断如果支持autoSize的话，会重新计算并设置文字的大小
    autoSizeText();
}
```
### onDraw
```java
protected void onDraw(Canvas canvas) {
		
	//重新开启跑马灯	
    restartMarqueeIfNeeded();

     // Draw the background for this view
     super.onDraw(canvas);
		 	
     final int compoundPaddingLeft = getCompoundPaddingLeft();
     final int compoundPaddingTop = getCompoundPaddingTop();
     final int compoundPaddingRight = getCompoundPaddingRight();
     final int compoundPaddingBottom = getCompoundPaddingBottom();
     final int scrollX = mScrollX;
     final int scrollY = mScrollY;
     final int right = mRight;
     final int left = mLeft;
     final int bottom = mBottom;
     final int top = mTop;
     final boolean isLayoutRtl = isLayoutRtl();
     final int offset = getHorizontalOffsetForDrawables();
     final int leftOffset = isLayoutRtl ? 0 : offset;
     final int rightOffset = isLayoutRtl ? offset : 0;

     final Drawables dr = mDrawables;
     if (dr != null) {
         /*
          * Compound, not extended, because the icon is not clipped
          * if the text height is smaller.
          */
         int vspace = bottom - top - compoundPaddingBottom - compoundPaddingTop;
         int hspace = right - left - compoundPaddingRight - compoundPaddingLeft;

         // IMPORTANT: The coordinates computed are also used in invalidateDrawable()
         // Make sure to update invalidateDrawable() when changing this code.
         if (dr.mShowing[Drawables.LEFT] != null) {
             canvas.save();
             canvas.translate(scrollX + mPaddingLeft + leftOffset,
                     scrollY + compoundPaddingTop + (vspace - dr.mDrawableHeightLeft) / 2);
             //将drawable绘制到屏幕上        
             dr.mShowing[Drawables.LEFT].draw(canvas);
             canvas.restore();
         }
		...这里省略类似的其他方向上drawable绘制
     int color = mCurTextColor;

     if (mLayout == null) {
         assumeLayout();
     }

     Layout layout = mLayout;

     if (mHint != null && mText.length() == 0) {
         if (mHintTextColor != null) {
             color = mCurHintTextColor;
         }
         layout = mHintLayout;
     }

     mTextPaint.setColor(color);
     mTextPaint.drawableState = getDrawableState();

     canvas.save();
     /*  Would be faster if we didn't have to do this. Can we chop the
         (displayable) text so that we don't need to do this ever?
     */

     int extendedPaddingTop = getExtendedPaddingTop();
     int extendedPaddingBottom = getExtendedPaddingBottom();

     final int vspace = mBottom - mTop - compoundPaddingBottom - compoundPaddingTop;
     final int maxScrollY = mLayout.getHeight() - vspace;

     float clipLeft = compoundPaddingLeft + scrollX;
     float clipTop = (scrollY == 0) ? 0 : extendedPaddingTop + scrollY;
     float clipRight = right - left - getCompoundPaddingRight() + scrollX;
     float clipBottom = bottom - top + scrollY
             - ((scrollY == maxScrollY) ? 0 : extendedPaddingBottom);

     if (mShadowRadius != 0) {
         clipLeft += Math.min(0, mShadowDx - mShadowRadius);
         clipRight += Math.max(0, mShadowDx + mShadowRadius);

         clipTop += Math.min(0, mShadowDy - mShadowRadius);
         clipBottom += Math.max(0, mShadowDy + mShadowRadius);
     }

     canvas.clipRect(clipLeft, clipTop, clipRight, clipBottom);

     int voffsetText = 0;
     int voffsetCursor = 0;

     // translate in by our padding
     /* shortcircuit calling getVerticaOffset() */
     if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) != Gravity.TOP) {
         voffsetText = getVerticalOffset(false);
         voffsetCursor = getVerticalOffset(true);
     }
     canvas.translate(compoundPaddingLeft, extendedPaddingTop + voffsetText);

     final int layoutDirection = getLayoutDirection();
     final int absoluteGravity = Gravity.getAbsoluteGravity(mGravity, layoutDirection);
     if (isMarqueeFadeEnabled()) {
         if (!mSingleLine && getLineCount() == 1 && canMarquee()
                 && (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) != Gravity.LEFT) {
             final int width = mRight - mLeft;
             final int padding = getCompoundPaddingLeft() + getCompoundPaddingRight();
             final float dx = mLayout.getLineRight(0) - (width - padding);
             canvas.translate(layout.getParagraphDirection(0) * dx, 0.0f);
         }

         if (mMarquee != null && mMarquee.isRunning()) {
             final float dx = -mMarquee.getScroll();
             canvas.translate(layout.getParagraphDirection(0) * dx, 0.0f);
         }
     }

     final int cursorOffsetVertical = voffsetCursor - voffsetText;

     Path highlight = getUpdatedHighlightPath();
     if (mEditor != null) {
         //如果是可编辑的文字，使用Editor的onDraw方法
         mEditor.onDraw(canvas, layout, highlight, mHighlightPaint, cursorOffsetVertical);
     } else {
         //非可编辑文字，使用Layout#draw方法
         layout.draw(canvas, highlight, mHighlightPaint, cursorOffsetVertical);
     }
	 //跑马灯	
     if (mMarquee != null && mMarquee.shouldDrawGhost()) {
         final float dx = mMarquee.getGhostOffset();
         canvas.translate(layout.getParagraphDirection(0) * dx, 0.0f);
         layout.draw(canvas, highlight, mHighlightPaint, cursorOffsetVertical);
     }

     canvas.restore();
}
```