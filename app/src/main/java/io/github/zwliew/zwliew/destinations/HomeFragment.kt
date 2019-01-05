package io.github.zwliew.zwliew.destinations


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import com.google.android.material.snackbar.Snackbar
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.viewUri
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(
    @LayoutRes override val layoutId: Int = R.layout.fragment_home,
    @MenuRes override val menuId: Int = R.menu.menu_generic_toolbar
) : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            github_button.setOnClickListener {
                viewUri(view, view.context, "https://github.com/zwliew")
            }
            medium_button.setOnClickListener {
                viewUri(this, context, "https://medium.com/@zwliew")
            }
            email_button.setOnClickListener {
                composeEmail(this, context, "zhaoweiliew@gmail.com")
            }
            stack_exchange_button.setOnClickListener {
                viewUri(this, context, "https://stackexchange.com/users/3912119/zwliew")
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
