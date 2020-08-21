package dao

import com.google.inject.Inject
import domains.{Usuario, UsuarioTableDef}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class UsuarioDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  var usuarios = TableQuery[UsuarioTableDef]

  def adicionar(usuario: Usuario): Future[String] = {
    dbConfig.db
      .run(usuarios += usuario)
      .map(res => "usuario successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def remover(id: Long): Future[Int] = {
    dbConfig.db.run(usuarios.filter(_.id === id).delete)
  }

  def atualizar(usuario: Usuario): Future[Int] = {
    dbConfig.db
      .run(usuarios.filter(_.id === usuario.id)
        .map(x => (x.nome, x.email))
        .update(usuario.nome, usuario.email)
      )
  }

  def buscar(id: Long): Future[Option[Usuario]] = {
    dbConfig.db.run(usuarios.filter(_.id === id).result.headOption)
  }

  def listar: Future[Seq[Usuario]] = {
    dbConfig.db.run(usuarios.result)
  }
}