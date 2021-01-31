package org.fireking.basic.image;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import org.fireking.ap.BR;

/**
 * Desc:
 */
public class MatrixEntity extends BaseObservable {

    private String value1 = "1";
    private String value2 = "0";
    private String value3 = "0";
    private String value4 = "0";
    private String value5 = "1";
    private String value6 = "0";
    private String value7 = "0";
    private String value8 = "0";
    private String value9 = "1";

    @Bindable
    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
        notifyPropertyChanged(BR.value1);
    }

    @Bindable
    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
        notifyPropertyChanged(BR.value2);
    }

    @Bindable
    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
        notifyPropertyChanged(BR.value3);
    }

    @Bindable
    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
        notifyPropertyChanged(BR.value4);
    }

    @Bindable
    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
        notifyPropertyChanged(BR.value5);
    }

    @Bindable
    public String getValue6() {
        return value6;
    }

    public void setValue6(String value6) {
        this.value6 = value6;
        notifyPropertyChanged(BR.value6);
    }

    @Bindable
    public String getValue7() {
        return value7;
    }

    public void setValue7(String value7) {
        this.value7 = value7;
        notifyPropertyChanged(BR.value7);
    }

    @Bindable
    public String getValue8() {
        return value8;
    }

    public void setValue8(String value8) {
        this.value8 = value8;
        notifyPropertyChanged(BR.value8);
    }

    @Bindable
    public String getValue9() {
        return value9;
    }

    public void setValue9(String value9) {
        this.value9 = value9;
        notifyPropertyChanged(BR.value9);
    }
}
