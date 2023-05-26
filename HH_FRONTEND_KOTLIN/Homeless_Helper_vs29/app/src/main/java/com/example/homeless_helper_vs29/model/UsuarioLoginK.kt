package com.example.homeless_helper_vs29.model



class UsuarioLoginK private constructor(usuariokot: Usuariokot) {

    companion object {

        private var instance: UsuarioLoginK? = null
        public var usuariokot: Usuariokot? = null


        fun createInstance(usuariokot: Usuariokot): UsuarioLoginK? {
            if (instance == null) {
                instance = UsuarioLoginK(usuariokot)
            }
            return instance
        }


        fun getInstance(): UsuarioLoginK? {
            return instance
        }

        fun deleteInstance() {
             instance = null
        }
/*
        fun updateUsuario(usuariokot: Usuariokot){
            this.usuariokot = usuariokot
        }
        */

    }
}
