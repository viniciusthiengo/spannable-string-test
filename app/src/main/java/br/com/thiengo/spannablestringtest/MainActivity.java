package br.com.thiengo.spannablestringtest;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SpannableString styledString
                = new SpannableString(
                "Texto largo\n\n"           // index 0 - 11
                + "Negrito\n\n"             // index 13 - 20
                + "Sublinhado\n\n"          // index 22 - 32
                + "Itálico\n\n"             // index 34 - 41
                + "Removido\n\n"            // index 43 - 51
                + "Colorido\n\n"            // index 53 - 61
                + "Destacado\n\n"           // index 63 - 72
                + "ECM2\n\n"                // "2" index 77 - 78
                + "ECM2\n\n"                // "2" index 83 - 84
                + "Url\n\n"                 //  index 86 - 89
                + "Clicável\n\n"            // index 91 - 99
                + "Fonte customizada\n\n"   // index 101 - 118
                + "Ícone  \n\n"             // index 126 - 127
                + "Emotion  \n\n" );        // index 137 - 138

        /* COLOCANDO O TEXTO AINDA MAIOR (2X MAIS) */
        styledString.setSpan(new RelativeSizeSpan(2f), 0, 11, 0);

        /* COLOCANDO O TETO COMO NEGRITO */
        styledString.setSpan(new StyleSpan(Typeface.BOLD), 13, 20, 0);

        /* COLOCANDO O TEXTO COMO SUBLINHADO */
        styledString.setSpan(new UnderlineSpan(), 22, 32, 0);

        /* COLOCANDO O TEXTO COMO ITÁLICO */
        styledString.setSpan(new StyleSpan(Typeface.ITALIC), 34, 41, 0);

        /* COLOCANDO O TEXTO COM UM TRAÇO NO MEIO */
        styledString.setSpan(new StrikethroughSpan(), 43, 51, 0);

        /* DEIXANDO O TEXTO COLORIDO (VERMELHO) */
        styledString.setSpan(new ForegroundColorSpan(Color.RED), 53, 61, 0);

        /* DESTACANDO O TEXTO EM AZUL */
        styledString.setSpan(new BackgroundColorSpan(Color.BLUE), 63, 72, 0);

        /* COLOCANDO O TEXTO ACIMA DA BASELINE */
        styledString.setSpan(new SuperscriptSpan(), 77, 78, 0);
        /* DIMINUINDO O TAMANHO DO TEXTO */
        styledString.setSpan(new RelativeSizeSpan(0.5f), 77, 78, 0);

        /* COLOCANDO O TEXTO ABAIXO DA BASELINE */
        styledString.setSpan(new SubscriptSpan(), 83, 84, 0);
        /* DIMINUINDO O TAMANHO DO TEXTO */
        styledString.setSpan(new RelativeSizeSpan(0.5f), 83, 84, 0);

        /* PERMITINDO QUE O TEXTO ABRA UMA URL QUANDO NO CLIQUE */
        styledString.setSpan(new URLSpan("http://www.google.com"), 86, 89, 0);

        /* PERMITINDO QUE O TEXTO SEJA CLICÁVEL */
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        };
        styledString.setSpan(clickableSpan, 91, 99, 0);

        /* COLOCANDO UMA FONTE CUSTOMIZADA */
        Typeface face = Typeface.createFromAsset( getAssets(), "Pacifico.ttf" );
        styledString.setSpan( new CustomTypefaceSpan("", face), 101, 118, Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO UM ÍCONE */
        Drawable d = getResources().getDrawable( R.drawable.ic_key );
        d.setBounds( 0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight() );
        ImageSpan span = new ImageSpan( d, ImageSpan.ALIGN_BASELINE );
        styledString.setSpan(span, 126, 127, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        /* COLOCANDO UM ÍCONE, MAS COM UM BITMAP */
        Bitmap emotion = BitmapFactory.decodeResource( getResources(), R.drawable.emotion );
        span = new ImageSpan( this, emotion, ImageSpan.ALIGN_BASELINE );
        styledString.setSpan(span, 137, 138, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


        TextView tv = (TextView) findViewById(R.id.tv_content);

        /* PARA QUE O TEXTO POSSA SER CLICÁVEL, TEMOS DE CONFIGURAR
         * UM LinkMovementMethod
         * */
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        tv.setText(styledString);


        /* RODANDO TAMBÉM EM UM EDITTEXT */
        SpannableStringBuilder ss = new SpannableStringBuilder( styledString );
        ss.setSpan( new StyleSpan( Typeface.BOLD_ITALIC ), 0, 11, 0 );
        EditText et = (EditText) findViewById(R.id.et_content);
        et.setText( ss );


        /* QUANDO O TEXTO É PERSISTIDO, TODO O
         * ESTILO É PERDIDO
         * */
        /*SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        sp.edit().putString( "span", ss.toString() ).apply();
        ss = new SpannableStringBuilder( sp.getString("span", "") );*/
        //tv.setText( ss );
    }
}
