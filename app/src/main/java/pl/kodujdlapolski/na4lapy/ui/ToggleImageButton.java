package pl.kodujdlapolski.na4lapy.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageButton;

import pl.kodujdlapolski.na4lapy.R;

/**
 * Created by Gosia on 2016-03-05.
 */
public class ToggleImageButton extends ImageButton implements Checkable {

    private OnCheckedChangeListener onCheckedChangeListener;
    private String colorAccent = String.format("#%06X", (0xFFFFFF & getResources().getColor(R.color.colorAccent)));
    private String colorPrimaryLight = String.format("#%06X", (0xFFFFFF & getResources().getColor(R.color.colorPrimaryLight)));

    public ToggleImageButton(Context context) {
        super(context);
    }

    public ToggleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChecked(attrs);
    }

    public ToggleImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChecked(attrs);
    }

    private void setChecked(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ToggleImageButton);
        setChecked(a.getBoolean(R.styleable.ToggleImageButton_android_checked, false));
        a.recycle();
    }

    @Override
    public boolean isChecked() {
        return isSelected();
    }

    @Override
    public void setChecked(boolean checked) {
        setSelected(checked);

        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, checked);
        }

        if (isChecked()) {
            //   but.setBackgroundColor(Color.GREEN); // it is possible to 1) change backgrounds or to 2) change color filter
            //   setColorFilter(Color.parseColor("#FF5722")); // our color accent from colors.xml
            this.setColorFilter(Color.parseColor(colorAccent));
        }
        else {
            //but.setBackgroundColor(Color.TRANSPARENT);
            this.setColorFilter(Color.parseColor(colorPrimaryLight));   //above API21 can be changed to clearColorFilter();
        }
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return onCheckedChangeListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ToggleImageButton buttonView, boolean isChecked);
    }

    public String getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(String colorAccent) {
        this.colorAccent = colorAccent;
    }

    public void setColorAccent(int color) {
        this.colorAccent = String.format("#%06X", (0xFFFFFF & getResources().getColor(color)));
    }
}
