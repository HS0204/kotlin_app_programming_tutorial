package hs.tutorials.c34_customadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView

/* 항목 구성을 위해 뷰를 가지고 있는 역할자 */
/* 어댑터에서 따로 뷰를 준비할 필요 없이 홀더 내에 있는 뷰를 이용할 수 있음 */

class DriveHolder(root: View) { // 어댑터에서 항목을 구성하기 위한 root 객체 전달 받음
    // 항목 구성을 위한 뷰 선언
    var typeImageView: ImageView
    var titleView: TextView
    var dateView: TextView
    var menuImageView: ImageView

    init {
        typeImageView = root.findViewById(R.id.custom_item_type_image)
        titleView = root.findViewById(R.id.custom_item_type_title)
        dateView = root.findViewById(R.id.custom_item_type_date)
        menuImageView = root.findViewById(R.id.custom_item_menu)
    }
}