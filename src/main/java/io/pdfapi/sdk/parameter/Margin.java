package io.pdfapi.sdk.parameter;

import io.pdfapi.sdk.exception.InvalidParameterValueException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobre on 09/11/2016.
 */
public class Margin {

    private int top;
    private int right;
    private int bottom;
    private int left;

    public Margin() {

    }

    public Margin(int top, int right, int bottom, int left) {
        setTop(top);
        setRight(right);
        setBottom(bottom);
        setLeft(left);
    }

    public Margin(int margin) {
        setTop(margin);
        setRight(margin);
        setBottom(margin);
        setLeft(margin);
    }

    public void setParameters(Map<String, Integer> parameters) {
        if (parameters.containsKey("top")) {
            this.setTop((int) parameters.get("top"));
        }

        if (parameters.containsKey("right")) {
            this.setRight((int) parameters.get("right"));
        }

        if (parameters.containsKey("bottom")) {
            this.setBottom((int) parameters.get("bottom"));
        }

        if (parameters.containsKey("left")) {
            this.setLeft((int) parameters.get("left"));
        }
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        if (top < 0) {
            throw new InvalidParameterValueException();
        }
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        if (right < 0) {
            throw new InvalidParameterValueException();
        }
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        if (bottom < 0) {
            throw new InvalidParameterValueException();
        }
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        if (left < 0) {
            throw new InvalidParameterValueException();
        }
        this.left = left;
    }

    public Map<String, Integer> getParameters() {
        Map<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("top", getTop());
        parameters.put("right", getRight());
        parameters.put("bottom", getBottom());
        parameters.put("left", getLeft());

        return parameters;
    }
}
