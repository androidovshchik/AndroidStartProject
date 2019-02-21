package androidovshchik.project.hyperion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidovshchik.project.R
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

internal class CustomModule : PluginModule() {

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View? {
        //RecordingsActivity.start(context)
        val view = layoutInflater.inflate(R.layout.hcustom_item_plugin, parent, false)
        return view
    }
}
