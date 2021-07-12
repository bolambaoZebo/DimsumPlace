package com.ninetysixgroup.dimsumplace

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ninetysixgroup.dimsumplace.model.homeFoodModel
import kotlinx.android.synthetic.main.activity_content.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), RecyclerAdapter.onItemClicked {

    private lateinit var dialog: AlertDialog.Builder
    private lateinit var dialogAlert: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDialog()
        home_recycler.layoutManager = GridLayoutManager(
            this,
            2,
            LinearLayoutManager.VERTICAL,
            false
        )
        home_recycler.adapter = RecyclerAdapter(this,getAllRacing(), this)

        we_bet.setOnClickListener{
            goToURL("3we")
        }

        we_horse.setOnClickListener{
            goToURL("3we-horse")
        }

        probit.setOnClickListener{
            goToURL("88probit")
        }

        probit_horse.setOnClickListener{
            goToURL("88probit-horse")
        }
    }

    private fun getAllRacing(): ArrayList<homeFoodModel> {
        val homeimages = resources.obtainTypedArray(R.array.array_image)
        val contentImage = resources.obtainTypedArray(R.array.array_image_details)
        val content = resources.getStringArray(R.array.array_content)
        val contentDetails = resources.obtainTypedArray(R.array.array_image_detailsmain)
        val list = ArrayList<homeFoodModel>()


        for (i in 0 until homeimages.length()){
            list.add(
                homeFoodModel(
                homeimages.getResourceId(i, -1),
                contentImage.getResourceId(i, -1),
                content[i],
                    contentDetails.getResourceId(i, -1),
                    0)
            )
        }

        return list
    }

    override fun onClicked(context: Context, content: String, image: Int, imageDetail: Int) {
       dialogAlert.show()

        Timer().schedule(2000) {
            val intent = Intent(context, Detail::class.java).apply {
                putExtra("content", "${content}")
                putExtra("image", image)
                putExtra("imageBody", imageDetail)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

    }

    fun setupDialog(){
        dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.loading_dialog, null)
        dialogView.setBackgroundColor(Color.TRANSPARENT)
        dialog.setView(dialogView)
        dialog.setCancelable(false)

        dialogAlert = dialog.create()
        dialogAlert.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun goToURL(input: String){
        val url = "https://asia3we.com/"
        val url2 = "https://www.3wehorse.com/"
        val url3 = "https://88probett.com"
        val url4 = "https://88prohorse.com"
        val openURL = Intent(Intent.ACTION_VIEW)

        when (input){
            "3we"->{
                openURL.data = Uri.parse(url)
                startActivity(openURL)
            }
            "3we-horse"->{
                openURL.data = Uri.parse(url2)
                startActivity(openURL)
            }
            "88probit"->{
                openURL.data = Uri.parse(url3)
                startActivity(openURL)
            }
            "88probit-horse"->{
                openURL.data = Uri.parse(url4)
                startActivity(openURL)
            }
            else->{
                println("Wrong Input");
            }
        }
    }

    override fun onStop() {
        super.onStop()

        if(dialogAlert.isShowing()){
            dialogAlert.dismiss()
        }
    }
}