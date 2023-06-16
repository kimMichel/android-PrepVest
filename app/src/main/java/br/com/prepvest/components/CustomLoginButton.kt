package br.com.prepvest.components

import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import br.com.prepvest.databinding.LayoutCustomLoginButtonBinding

class CustomLoginButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr){

    private var binding = LayoutCustomLoginButtonBinding.inflate(
        LayoutInflater.from(context), this,true
    )

    private var buttonText = ""

}