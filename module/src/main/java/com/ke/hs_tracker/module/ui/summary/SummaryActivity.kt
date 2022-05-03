package com.ke.hs_tracker.module.ui.summary

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ke.hs_tracker.module.R
import com.ke.hs_tracker.module.databinding.ModuleActivitySummaryBinding
import com.ke.hs_tracker.module.ui.chart.SummaryChartActivity
import com.ke.hs_tracker.module.ui.main.MainActivity
import com.ke.hs_tracker.module.ui.records.RecordsActivity
import com.ke.hs_tracker.module.ui.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryActivity : AppCompatActivity() {


    private lateinit var binding: ModuleActivitySummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModuleActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbar.apply {
            menu.clear()
            menu.add(
                0,
                0,
                0,
                R.string.module_settings
            ).setIcon(R.drawable.module_baseline_settings_white_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            menu.add(
                0,
                2,
                0,
                R.string.module_pie_chart
            ).setIcon(R.drawable.module_baseline_pie_chart_white_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            menu.add(0, 1, 0, R.string.module_view_all_games)

            setOnMenuItemClickListener {

                when (it.itemId) {
                    0 -> {
                        startActivity(Intent(this@SummaryActivity, SettingsActivity::class.java))
                    }
                    1 -> {
                        startActivity(Intent(this@SummaryActivity, RecordsActivity::class.java))
                    }

                    2 -> {
                        startActivity(
                            Intent(
                                this@SummaryActivity,
                                SummaryChartActivity::class.java
                            )
                        )
                    }
                }
                true
            }
        }

        binding.start.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        val fragments = listOf(RateByHeroFragment(), RateByDeckFragment())
        val titles = listOf(R.string.module_by_class, R.string.module_by_deck)

        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = fragments.size

            override fun createFragment(position: Int) = fragments[position]
        }
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(titles[position])
        }.attach()

    }


}