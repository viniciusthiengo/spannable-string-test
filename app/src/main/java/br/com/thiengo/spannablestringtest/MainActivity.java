package br.com.thiengo.spannablestringtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.tv_content);
        EditText et = (EditText) findViewById(R.id.et_content);


        SpannableString stringEstilizada = new SpannableString(
            "Texto largo\n\n"           /* index 0 - 11 */
            + "Negrito\n\n"             /* index 13 - 20 */
            + "Sublinhado\n\n"          /* index 22 - 32 */
            + "Itálico\n\n"             /* index 34 - 41 */
            + "Removido\n\n"            /* index 43 - 51 */
            + "Colorido\n\n"            /* index 53 - 61 */
            + "Destacado\n\n"           /* index 63 - 72 */
            + "ECM2\n\n"                /* "2" index 77 - 78 */
            + "ECM2\n\n"                /* "2" index 83 - 84 */
            + "Url\n\n"                 /* index 86 - 89 */
            + "Clicável\n\n"            /* index 91 - 99 */
            + "Fonte customizada\n\n"   /* index 101 - 118 */
            + "Ícone  \n\n"             /* index 126 - 127 */
            + "Emotion  \n\n"           /* index 137 - 138 */
        );

        /* COLOCANDO O TEXTO AINDA MAIOR (2X MAIS) */
        stringEstilizada.setSpan(
            new RelativeSizeSpan(2f),
            0,
            11,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO O TETO COMO NEGRITO */
        stringEstilizada.setSpan(
            new StyleSpan(Typeface.BOLD),
            13,
            20,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO UM PONTO / BULLET NA FRENTE DO TEXTO,
         * COMO EM LISTAS HTML
         * */
        stringEstilizada.setSpan(
            new BulletSpan(10),
            13,
            13,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE );

        /* COLOCANDO O TEXTO COMO SUBLINHADO */
        stringEstilizada.setSpan(
            new UnderlineSpan(),
            22,
            32,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO O TEXTO COMO ITÁLICO */
        stringEstilizada.setSpan(
            new StyleSpan(Typeface.ITALIC),
            34,
            41,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO O TEXTO COM UM TRAÇO NO MEIO */
        stringEstilizada.setSpan(
            new StrikethroughSpan(),
            43,
            51,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* DEIXANDO O TEXTO COLORIDO (VERMELHO) */
        stringEstilizada.setSpan(
            new ForegroundColorSpan(Color.RED),
            53,
            61,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* DESTACANDO O TEXTO EM AZUL */
        stringEstilizada.setSpan(
            new BackgroundColorSpan(Color.BLUE),
            63,
            72,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO O TEXTO ACIMA DA BASELINE */
        stringEstilizada.setSpan(
            new SuperscriptSpan(),
            77,
            78,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* DIMINUINDO O TAMANHO DO TEXTO PARA SIMULAR O
         * EFEITO DA TAG HTML <sup>
         * */
        stringEstilizada.setSpan(
            new RelativeSizeSpan(0.5f),
            77,
            78,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO O TEXTO ABAIXO DA BASELINE */
        stringEstilizada.setSpan(
            new SubscriptSpan(),
            83,
            84,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* DIMINUINDO O TAMANHO DO TEXTO PARA SIMULAR A
         * TAG HTML <sub>
         * */
        stringEstilizada.setSpan(
            new RelativeSizeSpan(0.5f),
            83,
            84,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* PERMITINDO QUE O TEXTO ABRA UMA URL QUANDO NO CLIQUE */
        stringEstilizada.setSpan(
            new URLSpan("http://www.thiengo.com.br"),
            86,
            89,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* PERMITINDO QUE O TEXTO SEJA CLICÁVEL */
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(
                    MainActivity.this,
                    "Link clicado",
                    Toast.LENGTH_SHORT ).show();
            }
        };
        stringEstilizada.setSpan(
            clickableSpan,
            91,
            99,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO UMA FONTE CUSTOMIZADA */
        Typeface face = Typeface.createFromAsset( getAssets(), "Pacifico.ttf" );
        stringEstilizada.setSpan(
            new CustomTypefaceSpan("", face),
            101,
            118,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        /* COLOCANDO UM ÍCONE */
        Drawable img = getResources().getDrawable( R.drawable.emotion );
        img.setBounds( 0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight() );
        ImageSpan spanImg = new ImageSpan( img, ImageSpan.ALIGN_BASELINE );
        stringEstilizada.setSpan(
            spanImg,
            126,
            127,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE );

        /* COLOCANDO UM ÍCONE, MAS COM UM BITMAP */
        Bitmap emotion = BitmapFactory.decodeResource( getResources(), R.drawable.emotion );
        spanImg = new ImageSpan( this, emotion, ImageSpan.ALIGN_BASELINE );
        stringEstilizada.setSpan(
            spanImg,
            137,
            138,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE );

        /* PARA QUE O TEXTO POSSA SER CLICÁVEL, TEMOS DE CONFIGURAR
         * UM LinkMovementMethod
         * */
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        et.setMovementMethod(LinkMovementMethod.getInstance());

        tv.setText( stringEstilizada, TextView.BufferType.SPANNABLE );
        et.setText( stringEstilizada );


        /* QUANDO O TEXTO É PERSISTIDO, TODO O
         * ESTILO É PERDIDO
         * */
        /*SpannableStringBuilder ss = new SpannableStringBuilder( stringEstilizada );
        SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        sp.edit().putString( "span", ss.toString() ).apply();
        et.setText( sp.getString("span", "") );*/
    }
}

