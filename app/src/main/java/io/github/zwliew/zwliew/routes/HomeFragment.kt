package io.github.zwliew.zwliew.routes


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.launchUrl
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            github_button.setOnClickListener {
                launchUrl(view, view.context, "https://github.com/zwliew")
            }
            medium_button.setOnClickListener {
                launchUrl(this, context, "https://medium.com/@zwliew")
            }
            email_button.setOnClickListener {
                composeEmail(this, context, "zhaoweiliew@gmail.com")
            }
            stack_exchange_button.setOnClickListener {
                launchUrl(this, context, "https://stackexchange.com/users/3912119/zwliew")
            }
        }
    }

    private fun composeEmail(view: View, context: Context, email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            startActivity(intent)
        } else {
            Snackbar.make(
                view, R.string.launch_email_error, Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
