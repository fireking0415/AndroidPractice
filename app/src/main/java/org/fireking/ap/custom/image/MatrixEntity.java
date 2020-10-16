package org.fireking.ap.custom.image;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import org.fireking.ap.BR;

/**
 * Desc:
 * <p>
 * Author: Wanggang
 * Date: 2020/10/16
 * Copyright: Copyright (c) 2016-2020
 * Company: @小牛科技
 * Update Comments:
 * 构建配置参见:
 *
 * @author Wanggang
 */
public class MatrixEntity extends BaseObservable {

    private int value1;
    private int value2;
    private int value3;
    private int value4;
    private int value5;
    private int value6;
    private int value7;
    private int value8;
    private int value9;

    @Bindable
    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
        notifyPropertyChanged(BR.value1);
    }

    @Bindable
    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
        notifyPropertyChanged(BR.value2);
    }

    @Bindable
    public int getValue3() {
        return value3;
    }

    public void setValue3(int value3) {
        this.value3 = value3;
        notifyPropertyChanged(BR.value3);
    }

    @Bindable
    public int getValue4() {
        return value4;
    }

    public void setValue4(int value4) {
        this.value4 = value4;
        notifyPropertyChanged(BR.value4);
    }

    @Bindable
    public int getValue5() {
        return value5;
    }

    public void setValue5(int value5) {
        this.value5 = value5;
        notifyPropertyChanged(BR.value5);
    }

    @Bindable
    public int getValue6() {
        return value6;
    }

    public void setValue6(int value6) {
        this.value6 = value6;
        notifyPropertyChanged(BR.value6);
    }

    @Bindable
    public int getValue7() {
        return value7;
    }

    public void setValue7(int value7) {
        this.value7 = value7;
        notifyPropertyChanged(BR.value7);
    }

    @Bindable
    public int getValue8() {
        return value8;
    }

    public void setValue8(int value8) {
        this.value8 = value8;
        notifyPropertyChanged(BR.value8);
    }

    @Bindable
    public int getValue9() {
        return value9;
    }

    public void setValue9(int value9) {
        this.value9 = value9;
        notifyPropertyChanged(BR.value9);
    }
}
