package com.dai.mylibrary.utils.format;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class StringUtils {

    public static String stringToHtmlMain(String string) {
        return "<font color='#B08D51'>" + string + "</font>";
    }

    /**
     * Intercept hyperlinks
     */
    public static SpannableString interceptHyperLink(final Context context, String str) {
        Spanned spanned = Html.fromHtml(str);
        SpannableString spannableString = new SpannableString(spanned);
        CharSequence text = spanned;
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable spannable = (Spannable) text;
            URLSpan[] urlSpans = spannable.getSpans(0, end, URLSpan.class);
            if (urlSpans.length == 0) {
                return null;
            }

            // view all links begin with "http://"
            for (URLSpan uri : urlSpans) {
                final String url = uri.getURL();
                if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            //FIXME 跳转
//                            Intent intent = new Intent(context, WebActivity.class);
//                            intent.putExtra(What.URL, url);
//                            intent.putExtra(What.TYPE, 0);
//                            context.startActivity(intent);
                        }
                    }, spannable.getSpanStart(uri), spannable.getSpanEnd(uri), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return spannableString;
    }


    public static void removeHyperLinkUnderline(Context context, TextView tv) {
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            Spannable spannable = (Spannable) tv.getText();
            URLSpanNoUnderline noUnderlineSpan = new URLSpanNoUnderline("");
            spannable.setSpan(noUnderlineSpan, 0, text.length(), Spanned.SPAN_MARK_MARK);
            //FIXME color
//            spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.link_text_color)), 0, text.length(), Spanned.SPAN_MARK_MARK);
        }
    }

    //remove underline
    public static class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
    }

    //隐藏手机号中间四位
    public static String encodePhone(String phone) {
        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            return phone;
        } else {
            return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        }
    }
}
