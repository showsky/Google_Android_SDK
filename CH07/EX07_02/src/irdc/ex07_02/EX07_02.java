package irdc.ex07_02;

/* import����class */
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

public class EX07_02 extends Activity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* �]�wContentView���۩w�q��MyView */
    MyView myView = new MyView(this);
    setContentView(myView);
  }
  
  /* �۩w�q�~��View��MyView */
  private class MyView extends View
  {
    public MyView(Context context)
    {
      super(context);
    }

    /* �мgonDraw() */
    @Override
    protected void onDraw(Canvas canvas)
    {
      super.onDraw(canvas);
      /* �]�w�I�����զ� */
      canvas.drawColor(Color.WHITE);
      
      Paint paint = new Paint();
      /* �h���� */
      paint.setAntiAlias(true);
      /* �]�wpaint���C�� */
      paint.setColor(Color.RED);
      /* �]�wpaint��style��STROKE�G�Ťߪ� */
      paint.setStyle(Paint.Style.STROKE);
      /* �]�wpaint���~�ؼe�� */
      paint.setStrokeWidth(3);
      
      /* �e�@�ӪŤ߶�� */
      canvas.drawCircle(40,40,30, paint);
      /* �e�@�ӪŤߥ���� */
      canvas.drawRect(10,90,70,150,paint);
      /* �e�@�ӪŤߪ���� */
      canvas.drawRect(10,170,70,200,paint);
      /* �e�@�ӪŤ߾��� */
      RectF re=new RectF(10,220,70,250);
      canvas.drawOval(re, paint);
      /* �e�@�ӪŤߤT���� */
      Path path = new Path();
      path.moveTo(10,330);
      path.lineTo(70,330);
      path.lineTo(40,270);
      path.close();
      canvas.drawPath(path, paint);
      /* �e�@�ӪŤ߱�� */
      Path path1 = new Path();
      path1.moveTo(10,410);
      path1.lineTo(70,410);
      path1.lineTo(55,350);
      path1.lineTo(25,350);
      path1.close();
      canvas.drawPath(path1, paint);
      
      /* �]�wpaint��style��FILL�G��� */
      paint.setStyle(Paint.Style.FILL);
      /* �]�wpaint���C�� */
      paint.setColor(Color.BLUE);
      
      /* �e�@�ӹ�߶� */
      canvas.drawCircle(120, 40, 30, paint);
      /* �e�@�ӹ�ߥ���� */
      canvas.drawRect(90,90,150,150,paint);
      /* �e�@�ӹ�ߪ���� */
      canvas.drawRect(90,170,150,200,paint);
      /* �e�@�ӹ�߾��� */
      RectF re2=new RectF(90,220,150,250);
      canvas.drawOval(re2, paint);
      /* �e�@�ӹ�ߤT���� */
      Path path2 = new Path();
      path2.moveTo(90,330);
      path2.lineTo(150,330);
      path2.lineTo(120,270);
      path2.close();
      canvas.drawPath(path2, paint);
      /* �e�@�ӹ�߱�� */
      Path path3 = new Path();
      path3.moveTo(90,410);
      path3.lineTo(150,410);
      path3.lineTo(135,350);
      path3.lineTo(105,350);
      path3.close();
      canvas.drawPath(path3, paint);

      /* �]�w���h�� */
      Shader mShader=new LinearGradient(0, 0,100,100,
          new int[]{Color.RED, Color.GREEN,Color.BLUE,Color.YELLOW},
          null, Shader.TileMode.REPEAT);
      paint.setShader(mShader);
      
      /* �e�@�Ӻ��h�⪺��� */
      canvas.drawCircle(200,40, 30, paint);
      /* �e�@�Ӻ��h�⪺����� */
      canvas.drawRect(170,90,230,150,paint);
      /* �e�@�Ӻ��h�⪺����� */
      canvas.drawRect(170,170,230,200,paint);
      /* �e�@�Ӻ��h�⪺���� */
      RectF re3=new RectF(170,220,230,250);
      canvas.drawOval(re3, paint);
      /* �e�@�Ӻ��h�⪺�T���� */
      Path path4 = new Path();
      path4.moveTo(170,330);
      path4.lineTo(230,330);
      path4.lineTo(200,270);
      path4.close();
      canvas.drawPath(path4, paint);
      /* �e�@�Ӻ��h�⪺��� */
      Path path5 = new Path();
      path5.moveTo(170,410);
      path5.lineTo(230,410);
      path5.lineTo(215,350);
      path5.lineTo(185,350);
      path5.close();
      canvas.drawPath(path5, paint);
      
      /* �g�r */
      paint.setTextSize(24);
      canvas.drawText(getResources().getString(R.string.str_text1),
                      240,50,paint);
      canvas.drawText(getResources().getString(R.string.str_text2),
                      240,120,paint);
      canvas.drawText(getResources().getString(R.string.str_text3),
                      240,190,paint);
      canvas.drawText(getResources().getString(R.string.str_text4),
                      240,250,paint);
      canvas.drawText(getResources().getString(R.string.str_text5),
                      240,320,paint);
      canvas.drawText(getResources().getString(R.string.str_text6),
                      240,390,paint);
    }
  }
}