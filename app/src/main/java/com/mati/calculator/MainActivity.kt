package com.mati.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.mati.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNumberClicked()
        onOperatorClicked()

    }

    private fun onNumberClicked() {

        binding.btnAdd0.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                appendText("0")
            }
        }

        binding.btnAdd1.setOnClickListener {
            appendText("1")
        }

        binding.btnAdd2.setOnClickListener {
            appendText("2")
        }

        binding.btnAdd3.setOnClickListener {
            appendText("3")
        }

        binding.btnAdd4.setOnClickListener {
            appendText("4")
        }

        binding.btnAdd5.setOnClickListener {
            appendText("5")
        }

        binding.btnAdd6.setOnClickListener {
            appendText("6")
        }

        binding.btnAdd7.setOnClickListener {
            appendText("7")
        }

        binding.btnAdd8.setOnClickListener {
            appendText("8")
        }

        binding.btnAdd9.setOnClickListener {
            appendText("9")
        }

        binding.btnDot.setOnClickListener {
            if (binding.txtAmaliat.text.isEmpty()) {
                appendText("0.")
            } else if (!binding.txtAmaliat.text.contains(".")) {
                appendText(".")
            }
        }

        binding.btnMosavi.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.txtAmaliat.text.toString()).build()
                val result = expression.evaluate()

                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = "= $longResult"
                } else {
                    binding.txtJavab.text = "= $result"
                }
            } catch (e: Exception) {
                binding.txtAmaliat.text = " "
                binding.txtJavab.text = " "
                Toast.makeText(this, "مشکلی پیش آمده!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onOperatorClicked() {
        binding.btnJam.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("+")
                }
            }
        }
        binding.btnManfi.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("-")
                }
            }
        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("*")
                }
            }
        }
        binding.btnTaghsim.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("/")
                }
            }
        }
        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }

        binding.btnParantezBaste.setOnClickListener {
            appendText(")")
        }

        binding.btnAc.setOnClickListener {
            binding.txtAmaliat.text = ""
            binding.txtJavab.text = ""
            binding.btnAc.text = "AC"
        }

        binding.btnPakkardan.setOnClickListener {
            val oldText = binding.txtAmaliat.text.toString()
            if (oldText.isNotEmpty()) {
                binding.txtAmaliat.text = oldText.substring(0, oldText.length - 1)
            }
            if (binding.txtAmaliat.text.isEmpty()) {
                binding.btnAc.text = "AC"
            }
            binding.txtJavab.text = ""
        }

    }

    private fun appendText(newText: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtAmaliat.text = ""
        }

        if (binding.txtAmaliat.text.isNotEmpty()) {
            binding.btnAc.text = "C"
        }

        binding.txtAmaliat.append(newText)
        binding.txtJavab.text = ""


        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTextAmaliat.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTextAmaliat.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTextAmaliat.scrollTo(binding.txtAmaliat.width, 0)
            }
        })

    }

}