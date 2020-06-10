package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gjy on 16/7/4.
 */

public class SuperCircleView extends View {

    private final String TAG = "SuperCircleView";
    private int[] mCircleColors = new int[]{0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00,
            0xFFFF0000};
    private int mViewWidth; //view的宽
    private int mViewHeight;    //view的高
    private int mViewCenterX;   //view宽的中心点
    private int mViewCenterY;   //view高的中心点
    private int mMinRadio; //最里面白色圆的半径
    private float mInnerRingWidth; //内圆环的宽度
    private float mOuterRingWidth; //外圆环的宽度
    private int mMinCircleColor;    //最里面圆的颜色
    private int mMidCircleColor;    //中间圆的颜色
    private int mSelectCircleRadio;    //外围小圆的半价
    private float mOffCircleDistance;    //外围小圆离取色器距离
    private float mLineWidth;    //外围线宽度
    private float markPointX = 0;
    private float markPointY = 0;
    private float  markerPointX=0;
    private float  markerPointY=0;
    private Paint mPaint;
    private Paint mPaints;
    private int color[] = new int[7];   //渐变颜色
    private int mColor;//当前选择颜色
    private RectF mInnerRectF; //内圆环的矩形区域
    private RectF mOuterRectF; //外圆环的矩形区域
    private RectF mLineRectF; //外围线的矩形区域
    Matrix  colorRingMatrix;
    private onTouchEvent onTouchEvent;
    private PointF downPointF = new PointF();//按下的位置

    public SuperCircleView(Context context) {
        this(context, null);
    }

    public SuperCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SuperCircleView);
        mMinRadio = a.getInteger(R.styleable.SuperCircleView_min_circle_radio, 400);
        mInnerRingWidth = a.getFloat(R.styleable.SuperCircleView_inner_ring_width, 40);
        mOuterRingWidth = a.getFloat(R.styleable.SuperCircleView_outer_ring_width, 40);
        mMinCircleColor = a.getColor(R.styleable.SuperCircleView_circle_color, context.getResources().getColor(R.color.white));
        mColor = a.getColor(R.styleable.SuperCircleView_selected_color, context.getResources().getColor(R.color.colorAccent));
        mMidCircleColor = a.getColor(R.styleable.SuperCircleView_ring_normal_color, context.getResources().getColor(R.color.transparent));
        mMidCircleColor = a.getColor(R.styleable.SuperCircleView_ring_normal_color, context.getResources().getColor(R.color.transparent));
        mSelectCircleRadio = a.getInteger(R.styleable.SuperCircleView_select_circle_radio, 5);
        mOffCircleDistance = a.getFloat(R.styleable.SuperCircleView_off_circle_distance, 5);
        mLineWidth = a.getFloat(R.styleable.SuperCircleView_line_width, 2);
        a.recycle();
        colorRingMatrix=new Matrix();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaints = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaints.setAntiAlias(true);
        this.setWillNotDraw(false);


    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mViewCenterX = mViewWidth / 2;
        mViewCenterY = mViewHeight / 2;
        Log.w("mMinRadio", "" + mMinRadio);
        Log.w("mMinRadio", "" + mInnerRingWidth);
        Log.w("mMinRadio", "" + mOuterRingWidth);
        float radius = mMinRadio + mInnerRingWidth + mOuterRingWidth + mOuterRingWidth / 2 + mOffCircleDistance + mSelectCircleRadio;
        Log.w("mMinRadio", "" + radius);
        mInnerRectF = new RectF(mViewCenterX - mMinRadio - mInnerRingWidth / 2,
                mViewCenterY - mMinRadio - mInnerRingWidth / 2,
                mViewCenterX + mMinRadio + mInnerRingWidth / 2,
                mViewCenterY + mMinRadio + mInnerRingWidth / 2);
        mOuterRectF = new RectF(mViewCenterX - mMinRadio - mInnerRingWidth - mOuterRingWidth / 2, mViewCenterY - mMinRadio - mInnerRingWidth - mOuterRingWidth / 2, mViewCenterX + mMinRadio + mInnerRingWidth + mOuterRingWidth / 2, mViewCenterY + mMinRadio + mInnerRingWidth + mOuterRingWidth / 2);
        mLineRectF = new RectF(mViewCenterX - radius - mLineWidth / 2,
                mViewCenterY - radius - mLineWidth / 2,
                mViewCenterX + radius + mLineWidth / 2,
                mViewCenterY + radius + mLineWidth / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画彩色圆
        drawColorRing(canvas);
        mPaint.setColor(mMidCircleColor);
        canvas.drawCircle(mViewCenterX, mViewCenterY, mMinRadio + mInnerRingWidth, mPaint);
        mPaint.setColor(mColor);
        canvas.drawCircle(mViewCenterX, mViewCenterY, mMinRadio, mPaint);
        //画内圆环
        drawNormalRing(canvas);
        mPaints.setColor(mColor);
        setColors(mColor);
        //画外
        /*colorRingMatrix.preTranslate(markPointX, markPointY);*/
        float radius = mMinRadio + mInnerRingWidth + mOuterRingWidth + mOuterRingWidth / 2+mOffCircleDistance+mSelectCircleRadio;
        canvas.drawCircle((float) (mViewCenterX+ radius * Math.sin(getRotationBetweenLines(mViewCenterX, mViewCenterY, markPointX, markPointY)* Math.PI / 180)),(float) (mViewCenterY - radius * Math.cos( getRotationBetweenLines(mViewCenterX, mViewCenterY, markPointX, markPointY)* Math.PI / 180 )), mSelectCircleRadio, mPaints);
        drawLine(canvas);
    }

    /**
     * 画彩色圆环
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        Paint ringColorPaint = new Paint(mPaints);
        ringColorPaint.setStyle(Paint.Style.STROKE);
        ringColorPaint.setStrokeWidth(mLineWidth);
        ringColorPaint.setShader(new SweepGradient(mViewCenterX, mViewCenterY, mCircleColors, null));
        canvas.drawArc(mLineRectF, angleStart, 90, false, ringColorPaint);
    }

    /**
     * 画彩色圆环
     *
     * @param canvas
     */
    private void drawColorRing(Canvas canvas) {
        Paint ringColorPaint = new Paint(mPaint);
        ringColorPaint.setStyle(Paint.Style.STROKE);
        ringColorPaint.setStrokeWidth(mOuterRingWidth);
        ringColorPaint.setShader(new SweepGradient(mViewCenterX, mViewCenterY, mCircleColors, null));
        canvas.drawCircle(mViewCenterX, mViewCenterY, mMinRadio + mInnerRingWidth + mOuterRingWidth, ringColorPaint);//色盘大小
    }

    /**
     * 画默认圆环
     *
     * @param canvas
     */
    private void drawNormalRing(Canvas canvas) {
        Paint ringNormalPaint = new Paint(mPaint);
        ringNormalPaint.setStyle(Paint.Style.STROKE);
        ringNormalPaint.setStrokeWidth(mInnerRingWidth);
        ringNormalPaint.setColor(mMidCircleColor);
        canvas.drawArc(mInnerRectF, 270, 360, false, ringNormalPaint);
    }

    int angleStart=0;

    private void setColors(int color) {
        float[] hsv = {0, 0, 1};
        Color.colorToHSV(color, hsv);
        //根据hsv角度及半径获取坐标
        //根据角度和半径获取坐标
        float radian = (float) Math.toRadians(-hsv[0]);
        float colorDotRadius = hsv[1] * (mMinRadio + mInnerRingWidth + mOuterRingWidth+mOuterRingWidth/2);
        float colorDotX = (float) (mViewCenterX + Math.cos(radian) * colorDotRadius);
        float colorDotY = (float) (mViewCenterY + Math.sin(radian) * colorDotRadius);
        //设置marker位置
        markPointX = colorDotX;
        markPointY = colorDotY;
       // Log.w("angle", "" + markPointX+" "+markPointY);
        angleStart= getRotationBetweenLiness(mViewCenterX, mViewCenterY, markPointX, markPointY);
        Log.w("angle", "" + angleStart);

        mColor = getColorAtPoint(colorDotX, colorDotY);//设置当前颜色
    }

    public void setColor(int color) {
        float[] hsv = {0, 0, 1};
        Color.colorToHSV(color, hsv);
        //根据hsv角度及半径获取坐标
        //根据角度和半径获取坐标
        float radian = (float) Math.toRadians(-hsv[0]);
        float colorDotRadius = hsv[1] * (mMinRadio + mInnerRingWidth + mOuterRingWidth+mOuterRingWidth/2);
        float colorDotX = (float) (mViewCenterX + Math.cos(radian) * colorDotRadius);
        float colorDotY = (float) (mViewCenterY + Math.sin(radian) * colorDotRadius);
        //设置marker位置
        markPointX = colorDotX;
        markPointY = colorDotY;
        mColor = getColorAtPoint(colorDotX, colorDotY);//设置当前颜色
        Log.w("mColor", "mColor" + mColor);
          invalidate();
    }

    private int getColorAtPoint(float eventX, float eventY) {
        float x = eventX - mViewCenterX;
        float y = eventY - mViewCenterY;
        double r = Math.sqrt(x * x + y * y);
        float[] hsv = {0, 0, 1};
        hsv[0] = (float) (Math.atan2(y, -x) / Math.PI * 180f) + 180;
        hsv[1] = Math.max(0f, Math.min(1f, (float) (r / (mMinRadio + mInnerRingWidth + mOuterRingWidth+mOuterRingWidth/2))));
        return Color.HSVToColor(hsv);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downPointF.x = event.getX();
                downPointF.y = event.getY();
            case MotionEvent.ACTION_MOVE:
                update(event);
                return true;
            case MotionEvent.ACTION_UP:

                break;
            default:
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void update(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        updateSelector(x, y);

    }


    private void updateSelector(float eventX, float eventY) {
        float x = eventX - mViewCenterX;
        float y = eventY - mViewCenterY;
        double r = Math.sqrt(x * x + y * y);
        //判断是否在圆内
        if (r > (mMinRadio + mInnerRingWidth + mOuterRingWidth+mOuterRingWidth/2)) {
            //不在圆形范围内
            return;
        }
        //同时旋转外圈滑动按钮
       colorRingMatrix.preRotate(getRotationBetweenLines(mViewCenterY, mViewCenterY, eventX, eventY), mViewCenterX, mViewCenterY);
        markPointX = x + mViewCenterX;
        markPointY = y + mViewCenterY;
        markerPointX = markPointX;//改变点标记位置
        markerPointY = markPointY;
        mColor = getColorAtPoint(eventX, eventY);//获取到的颜色
        if (onTouchEvent!=null){
            onTouchEvent.Touch(mColor);
        }
        invalidate();
    }

    /**
     * 获取两条线的夹角
     *
     * @param centerX
     * @param centerY
     * @param xInView
     * @param yInView
     * @return
     */
    public static int getRotationBetweenLines(float centerX, float centerY, float xInView, float yInView) {
        double rotation = 0;
        double k1 = (double) (centerY - centerY) / (centerX * 2 - centerX);
        double k2 = (double) (yInView - centerY) / (xInView - centerX);
        double tmpDegree = Math.atan((Math.abs(k1 - k2)) / (1 + k1 * k2)) / Math.PI * 180;

        if (xInView > centerX && yInView < centerY) {  //第一象限
            rotation = 90 - tmpDegree;
        } else if (xInView > centerX && yInView > centerY) //第二象限
        {
            rotation = 90 + tmpDegree;
        } else if (xInView < centerX && yInView > centerY) { //第三象限
            rotation = 270 - tmpDegree;
        } else if (xInView < centerX && yInView < centerY) { //第四象限
            rotation = 270 + tmpDegree;
        } else if (xInView == centerX && yInView < centerY) {
            rotation = 0;
        } else if (xInView == centerX && yInView > centerY) {
            rotation = 180;
        }else if (k1 == 0 && k2 ==0) {
            rotation = 90;
        }

        return (int) rotation;
    }

    private int getRotationBetweenLiness(float centerX, float centerY, float xInView, float yInView) {
        double rotation = 0;
        double k1 = (double) (centerY - centerY) / (centerX * 2 - centerX);
        double k2 = (double) (yInView - centerY) / (xInView - centerX);
        double tmpDegree = Math.atan((Math.abs(k1 - k2)) / (1 + k1 * k2)) / Math.PI * 180;
        if (xInView > centerX && yInView < centerY) {  //第一象限
            rotation = 90 - tmpDegree;
            rotation=180+rotation;
        } else if (xInView > centerX && yInView > centerY) //第二象限
        {
            rotation = 270 + tmpDegree;

        } else if (xInView < centerX && yInView > centerY) { //第三象限
            rotation = 90 - tmpDegree;
        } else if (xInView < centerX && yInView < centerY) { //第四象限
            rotation = 90 + tmpDegree;
        } else if (xInView == centerX && yInView < centerY) {
            rotation = 0;
        } else if (xInView == centerX && yInView > centerY) {
            rotation = 180;
        }else if (k1 == 0 && k2 ==0) {
            rotation = 270;
        }
        return (int) rotation;
    }

    public void setOnTouchEvent(SuperCircleView.onTouchEvent onTouchEvent) {
        this.onTouchEvent = onTouchEvent;
    }

    private   interface  onTouchEvent{
       void Touch(int Color);
    }
}
