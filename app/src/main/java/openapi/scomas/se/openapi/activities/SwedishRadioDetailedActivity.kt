package openapi.scomas.se.openapi.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detailed.*
import openapi.scomas.se.openapi.R
import openapi.scomas.se.openapi.viewmodels.MasterViewModel



/**
 * Project : OpenAPI
 * Package Name : openapi.scomas.se.openapi.activities
 *
 *
 */
class SwedishRadioDetailedActivity :  AppCompatActivity(){

    lateinit var mViewModel : MasterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_detailed)
        my_toolbar.title = resources.getString(R.string.nav_swedish_radio_title)
        setSupportActionBar(my_toolbar)
        my_toolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_back_button))


        mViewModel = ViewModelProviders.of(this).get(MasterViewModel::class.java)


        val url = intent.getStringExtra("radioURL")
        val name = intent.getStringExtra("radioName")
        val desc = intent.getStringExtra("radioDescription")

        Glide.with(this).load(url).into(radioImage)
        radioInfo.text = desc
        radioTitle.text = name

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onStop() {
        super.onStop()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        this.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        return super.onOptionsItemSelected(item)
    }
}