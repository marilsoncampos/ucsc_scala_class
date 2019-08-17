import org.scalatest.Matchers._
import org.scalatest._

class TestCaseExample extends WordSpec with MustMatchers {
  "The 'Spark with Scala' string" should {
    "contain 16 characters" in {
      "Spark with Scala".length === 16
    }

    "start with 'Spark'" in {
      "Spark with scala" should startWith("Spark")
    }

    "end with 'scala'" in {
      "Spark with scala" should endWith("scala")
    }
  }

  "Strings" should {
    val string =
      """Scala is a great tool.
        |It fuels the Spark engine.
        |The next gen big data plataform.
        |""".stripMargin.replace(System.lineSeparator, "\n")

    "compare normally" in {
      string.length shouldBe 83
      string should startWith("Scala is a great tool.")
      string should include("It fuels the Spark engine.")
      string should endWith("plataform.\n")
    }

    "compare with regexes" in {
      string should fullyMatch regex "(?s)Scala .* Spark .*"
      string should startWith regex "Scala .*"
      string should include regex "It fuels .*"
      string should endWith regex "plataform.\n"
    }
  }

  "Work with Option" should {
    "work for Some values" in {
      val option = Some(3)
      option shouldBe defined
      option.get shouldBe 3
      option.get should be < 7
      option should contain oneOf(3, 5, 7, 9)
      List(3, 5, 7, 9) should contain(option.get)
      option should not contain oneOf(7, 8, 9)
      List(5, 7, 9) should not contain option.get
    }

    "Work with None" in {
      val option: Option[Int] = None
      option shouldBe empty // the following are all equivalent:
      option shouldEqual None
      option shouldBe None
      option should ===(None)
    }
  }
}

