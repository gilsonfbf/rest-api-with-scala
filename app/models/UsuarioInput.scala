package models

import play.api.data.Form
import play.api.data.Forms.{mapping, _}

case class UsuarioInput(nome: String, email: String)

object UsuarioForm {
  val form = Form(
    mapping(
      "nome" -> nonEmptyText,
      "email" -> nonEmptyText
    )(UsuarioInput.apply)(UsuarioInput.unapply)
  )
}