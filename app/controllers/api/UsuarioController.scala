package controllers.api

import domains.Usuario
import javax.inject._
import models.UsuarioForm
import play.api.libs.json._
import play.api.mvc._
import services.UsuarioService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UsuarioController @Inject()(cc: ControllerComponents,
                                  usuarioService: UsuarioService) extends AbstractController(cc) {

  implicit val usuarioFormat = Json.format[Usuario]

  def listarUsuarios = Action.async { implicit request: Request[AnyContent] =>
    usuarioService.listarUsuario map { usuarios =>
      Ok(Json.toJson(usuarios))
    }
  }

  def buscarUsuario(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    usuarioService.buscarUsuario(id) map { usuario =>
      Ok(Json.toJson(usuario))
    }
  }

  def adicionarUsuario() = Action.async { implicit request: Request[AnyContent] =>
    UsuarioForm.form.bindFromRequest.fold(

      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error! " + errorForm.errors.toString()))
      },
      data => {
        val novoUsuario = Usuario(0, data.nome, data.email)
        usuarioService.adicionarUsuario(novoUsuario).map(_ => Redirect(routes.UsuarioController.listarUsuarios))
      })
  }


  def atualizarUsuario(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    UsuarioForm.form.bindFromRequest.fold(

      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error! " + errorForm.errors.toString()))
      },
      data => {
        val usuario = Usuario(id, data.nome, data.email)
        usuarioService.atualizarUsuario(usuario).map(_ => Redirect(routes.UsuarioController.listarUsuarios))
      })
  }

  def removerUsuario(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    usuarioService.removerUsuario(id) map { res =>
      Redirect(routes.UsuarioController.listarUsuarios)
    }
  }
}