package org.tryndusi.model.render.svg;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public abstract class SVGElement {

    @XStreamAsAttribute
    private String stroke = "black";

    @XStreamAlias("stroke-width")
    @XStreamAsAttribute
    private String strokeWidth = "1";

    @XStreamAlias("stroke-opacity")
    @XStreamAsAttribute
    private String strokeOpacity = "1";

    @XStreamAlias("stroke-dasharray")
    @XStreamAsAttribute
    private String strokeDashArray = "0";

    @XStreamAsAttribute
    private String fill = "none";

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(String srokeWidth) {
        this.strokeWidth = srokeWidth;
    }

    public String getStrokeOpacity() {
        return strokeOpacity;
    }

    public void setStrokeOpacity(String srokeOpacity) {
        this.strokeOpacity = srokeOpacity;
    }

    public String getStrokeDashArray() {
        return strokeDashArray;
    }

    public void setStrokeDashArray(String strokeDashArray) {
        this.strokeDashArray = strokeDashArray;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }
}
