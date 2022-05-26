package hs.tutorials.c51_permissioncheckandrequest

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 퍼미션 요청 두번째 방법
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            // 유저에 의해 퍼미션이 조정되었을 때 콜백
            if(it){
                Toast.makeText(this, "퍼미션 체크 확인", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "퍼미션 체크 거절", Toast.LENGTH_SHORT).show()
            }
        }

        val status = ContextCompat.checkSelfPermission(
            this,
            "android.permission.ACCESS_FIND_LOCATION"
        )

        if(status == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "퍼미션 체크 확인", Toast.LENGTH_SHORT).show()
        }else{
            // 퍼미션 미체크 시 요청 첫번째 방법
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf<String>("android.permission.ACCESS_FIND_LOCATION"),
//                100
//            )
            // 퍼미션 미체크 시 요청 두번째 방법
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // 퍼미션 요청 사후처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray // 퍼미션이 조정된 정보
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "퍼미션 체크 확인", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "퍼미션 체크 거절", Toast.LENGTH_SHORT).show()
        }
    }
}