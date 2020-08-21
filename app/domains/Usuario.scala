package domains

import slick.jdbc.MySQLProfile.api._

case class Usuario(id: Long, nome: String, email: String)

class UsuarioTableDef(tag: Tag) extends Table[Usuario](tag, "usuario") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def nome = column[String]("nome")

  def email = column[String]("email")

  override def * = (id, nome, email) <> (Usuario.tupled, Usuario.unapply)

}

