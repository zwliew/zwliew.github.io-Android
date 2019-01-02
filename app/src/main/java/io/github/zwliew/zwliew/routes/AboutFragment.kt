package io.github.zwliew.zwliew.routes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.launchUrl

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        with(view) {
            val resumeButton = findViewById<Button>(R.id.resume_button)
            resumeButton.setOnClickListener {
                launchUrl(this, context, "https://zwliew.netlify.com/res/resume.pdf")
            }
        }
        return view
    }
}
