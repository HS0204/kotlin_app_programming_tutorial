package hs.tutorials.c34_customadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

/*
resID: 항목 구성 위한 layout xml 파일
data: 항목 구성 위한 data
*/
class DriveAdapter(cxt: Context, val resId: Int, val data: MutableList<DriveVO>): ArrayAdapter<DriveVO>(cxt, resId) {

    override fun getCount(): Int { // 항목의 data 사이즈 판단
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View { // 항목 구성
        var convertView = convertView

        if (convertView == null){ // 뷰가 준비 되어있지 않을 때
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater // 뷰 생성 작업 --> xml 파일로 정의된 레이아웃 리소스를 초기화 시켜서 뷰 객체 생성
            convertView = inflater.inflate(resId, null) // resID --> xml 파일에 있는 대로 뷰 객체 생성하라는 명령

            val holder = DriveHolder(convertView)
            convertView!!.tag = holder
        }

        // 뷰 얻기
        val holder = convertView.getTag() as DriveHolder
        val typeImageView = holder.typeImageView
        val titleView = holder.titleView
        val dateView = holder.dateView
        val menuImageView = holder.menuImageView

        // 데이터 처리
        val (title, date, type) = data[position]

        titleView.text = title
        dateView.text = date
        if(type == "doc"){
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_doc, null
                )
            )
        }else if(type == "file"){
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_file, null
                )
            )
        }else if(type == "img"){
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_image, null
                )
            )
        }

        menuImageView.setOnClickListener {
            Toast.makeText(context, "$title menu click", Toast.LENGTH_SHORT).show()
        }

        return convertView

    }
}