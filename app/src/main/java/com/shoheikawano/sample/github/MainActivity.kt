package com.shoheikawano.sample.github

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.shoheikawano.sample.github.data.remote.api.GitHubApiService
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    init {
        Timber.plant(Timber.DebugTree())
    }

    private val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .addInterceptor {
                it.proceed(
                        it.request().newBuilder()
                                .addHeader("Accept", "application/vnd.github.v3+json")
                                .addHeader("Authorization", "token ${BuildConfig.GITHUB_OAUTH_TOKEN}")
                                .build()
                )
            }
            .build()

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(
                    MoshiConverterFactory.create(
                            Moshi.Builder().build()
                    )
            )
            .client(client)
            .baseUrl("https://api.github.com/")
            .build()

    private val service = retrofit.create(GitHubApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            lifecycleScope.launch {
                service.searchAndroidKotlinRepos()
                Snackbar.make(it, "API call success", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
