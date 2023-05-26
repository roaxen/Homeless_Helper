package com.example.homeless_helper_vs29.utils

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.model.Usuariokot
import java.util.*

/**
 * Clase que se encarga de la validacion de los campos de los formularios, Implementada para cualquier formulario
 * Contiene patter para Email y Password, Enum con Errores
 *
 * */
class Validator
{
    companion object
    {
        private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        private val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\$&*+=?-]).{8}$".toRegex()

        private val colorSuccesful = R.color.INPUT_OKEY;
        private val colorError = R.color.INPUT_ERROR;

         val MSJ_SUCCESFUL = "Succesful";

        enum class MSJ_ERROR(val mensaje: String) {
            FORMATO_EMPTY("El campo es obligatorio."),
            FORMATO_INVALIDO_NUMERICO("Introduce numeros"),
            FORMATO_EMAIL("El correo electrónico no es válido."),
            FORMATO_PASS_SIZE("La contraseña debe tener 8 caracteres como minimo."),
            FORMATO_PASS_UPPERCASE("La contraseña debe tener mayúsculas."),
            FORMATO_PASS_LOWERCASE("La contraseña debe tener minúsculas."),
            FORMATO_PASS_SPECIALKEY("La contraseña debe tener(!@#\$&*+=?-)."),
            FORMATO_REPASS("Los password no coinciden."),
            FORMATO_USER_PASS_LOGIN("La cotraseña no correcponde a la cuenta"),
            FORMATO_ADULT18("Tienes que + 18 ")
        }

        /**
         * Metodo que comprueba si el valr or introducido en la String tiene valor (eliminando los espacios), de no ser asi
         * pondra un mensaje de error en el TextView, es necesario pasarle el contex de la Activity, devolvera un boolean
         * diciendo si el campo estaba vacio
         * @param String valor del input
         * @param TextView texto que mostrara el error
         * @param Context Activity donde se ejecuta
         * @return Boolean True-> tiene informacion False->Esta vacio
         * */
        fun isThisInputFull(text: String, textError: TextView , context: Context): Boolean
        {
            val textoLimpio = text.trim()

            if(textoLimpio.isEmpty() || textoLimpio == null)
            {
                textError.setText(MSJ_ERROR.FORMATO_EMPTY.mensaje);
                textError.setTextColor(ContextCompat.getColor(context, colorError));
                return false;
            }
            textError.setText("");
            //textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
            return true;
        }

        fun isInputNumberValid(number: String, textError: TextView, context: Context): Boolean {
            val trimmedNumber = number.trim()

            if (trimmedNumber.isEmpty() || trimmedNumber.toIntOrNull() == null) {
                textError.setText(MSJ_ERROR.FORMATO_INVALIDO_NUMERICO.mensaje)
                textError.setTextColor(ContextCompat.getColor(context, colorError))
                return false
            }

            textError.setText("")
            return true
        }

        fun isInputEmailValid(email: String, textError: TextView , context: Context): Boolean
        {
            if (isThisInputFull(email, textError, context))
            {
                if(emailPattern.matches(email) == false)
                {
                    textError.setText(MSJ_ERROR.FORMATO_EMAIL.mensaje);
                    textError.setTextColor(ContextCompat.getColor(context, colorError))
                    return false;
                }
                textError.setText(MSJ_SUCCESFUL);
                textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
                return true;
            }
                return false;
        }


        fun isInputPassValid(password: String, textError: TextView , context: Context): Boolean
        {
            if (isThisInputFull(password, textError, context))
            {
                if (passwordPattern.matches(password) == false)
                {
                    if (password.length < 8)
                    {
                        textError.setText(MSJ_ERROR.FORMATO_PASS_SIZE.mensaje);
                        textError.setTextColor(ContextCompat.getColor(context, colorError))
                        return false;
                    }
                    else if (!password.contains(Regex("[A-Z]")))
                    {
                        textError.setText(MSJ_ERROR.FORMATO_PASS_UPPERCASE.mensaje);
                        textError.setTextColor(ContextCompat.getColor(context, colorError))
                        return false;
                    }
                    else if (!password.contains(Regex("[a-z]")))
                    {
                        textError.setText(MSJ_ERROR.FORMATO_PASS_LOWERCASE.mensaje);
                        textError.setTextColor(ContextCompat.getColor(context, colorError))
                        return false;
                    }
                    else if (!password.contains(Regex("[!@#\$&*+=?-]")))
                    {
                        textError.setText(MSJ_ERROR.FORMATO_PASS_SPECIALKEY.mensaje);
                        textError.setTextColor(ContextCompat.getColor(context, colorError))
                        return false;
                    }
                }
                textError.setText(MSJ_SUCCESFUL);
                textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
                return true
            }
            return false;
        }

        fun isInputRePassValid(rePassword: String, password: String, textError: TextView , context: Context): Boolean
        {
            if (isThisInputFull(rePassword, textError, context))
            {
                if (rePassword.equals(password) == false)
                {
                    textError.setText(MSJ_ERROR.FORMATO_REPASS.mensaje);
                    textError.setTextColor(ContextCompat.getColor(context, colorError))
                    return false;
                }
                textError.setText(MSJ_SUCCESFUL);
                textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
                return true;
            }

            return false;
        }


        fun isOver18(fechaNacimiento: Calendar, textError: TextView , context: Context): Boolean
        {
            val fechaActual = Calendar.getInstance()
            val diffYears = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR)
            val diffMonths = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH)
            val diffDays = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH)

            if (diffYears > 18)  // mayor de 18
            {
                textError.setText(MSJ_SUCCESFUL);
                textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
                return true;
            }
            else if (diffYears == 18 && diffMonths > 0) // los cumple este año comprobar si ya paso el mes
            {
                textError.setText(MSJ_SUCCESFUL);
                textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
                return true;
            }
            else if (diffYears == 18 && diffMonths == 0 && diffDays >= 0) // los cumple este año y este mes comprobar si paso el dia
            {
                textError.setText(MSJ_SUCCESFUL);
                textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
                return true;
            }
            textError.setText(MSJ_ERROR.FORMATO_ADULT18.mensaje);
            textError.setTextColor(ContextCompat.getColor(context, colorError))
            return false;
        }

        /**
         * Enviar el password cifrado ya para compararlo con el de usuario logeado que lo tenemos cifrado
         * */
        fun isPasswordUserLogin(passwordActual: String, textError: TextView , context: Context): Boolean
        {
            if (isThisInputFull(passwordActual, textError, context))
            {
                if (passwordActual.equals(UsuarioLoginK.usuariokot!!.clave) == false)
                {
                    textError.setText(MSJ_ERROR.FORMATO_USER_PASS_LOGIN.mensaje);
                    textError.setTextColor(ContextCompat.getColor(context, colorError))
                    return false;
                }
                textError.setText(MSJ_SUCCESFUL);
                textError.setTextColor(ContextCompat.getColor(context, colorSuccesful))
                return true;
            }
            return false;
        }
    }
}

    /*
// EJEMPLO DE USO DE  isPasswordError

val password = "Ejemplo1"
val (isValid, errorMessage) = Validator.isPasswordValid(password)

if (isValid) {
    // La contraseña es válida, continúa con el proceso de registro
} else {
    // La contraseña no es válida, muestra un mensaje de error al usuario
    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
}



     */


