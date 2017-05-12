package com.company;

import javafx.scene.control.Label;

/**
 * Created by kareem on 12/05/17.
 */
public class ChartPortion extends Label {
    float ratio;

    public ChartPortion(String text, float ratio) {
        super(text);
        this.ratio = ratio;
    }
    public void setWidthWithRatio(float fullWidth)
    {
        setPrefWidth(fullWidth * ratio - 1);
    }
}
