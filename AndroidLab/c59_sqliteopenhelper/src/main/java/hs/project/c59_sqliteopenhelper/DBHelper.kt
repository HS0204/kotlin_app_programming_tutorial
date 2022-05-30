package hs.project.c59_sqliteopenhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val studentSql = """
            create table tb_member (
            _id integer primary key autoincrement,
            name not null,
            email,
            phone)
        """
        db?.execSQL(studentSql)
        db?.execSQL("insert into tb_member (name, email, phone) values ('hanseul', 'hs@naver.com', '01000000000')")
    }

    // 상위 클래스에 들어가는 버전정보가 바뀌었을 때 사용
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table tb_student")
        onCreate(db)
    }
}