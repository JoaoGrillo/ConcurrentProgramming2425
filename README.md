# Programação Concorrente 24/25
**Neste repositório você encontrará:**
  - Códigos que auxiliam o aprendizado das Aulas Teóricas
  - Exercícios resolvidos das Aulas Práticas

**Configure seu computador para compilar e executar o Scala.**
1. Instale o SBT http://www.scala-sbt.org.
  Se o SBT não estiver instalado em sua máquina Linux, você pode instalá-lo localmente usando os seguintes comandos no
  Prompt de Comando:
    > cd; wget https://github.com/sbt/sbt/releases/download/v1.10.11/sbt=1.10.11.zip; unzip sbt=1.10.11.zip;
    export PATH=$PATH:$HOME/sbt/bin
2. Abra um Prompt de Comando.
3. Crie uma pasta para o projeto:
    > mkdir cp2425
    > cd cp2425
4. Crie um diretório de código-fonte para nossos exercícios:
    > mkdir -p src/main/scala/cp/lablessons/
5. Crie o arquivo de configuração build.sbt abaixo na raiz do cp2425 (linhas em branco são obrigatórias).
    name := "CP2425"
    version := "1.0"
    scalaVersion := "2.12.18"
6. Crie seu programa hello-world. Use seu editor favorito EDIT.
    > mkdir src/main/scala/cp/lablessons/lesson1
    > EDIT src/main/scala/cp/lablessons/lesson1/HelloWorld.scala
    package cp.lablessons.lesson1
    object HelloWorld extends App {
      println("Olá, mundo!")
    }
7. Retorne ao terminal e execute o SBT para iniciar um shell interativo:
    > sbt
8. Run your program:
    sbt> run
Note: You can also compile, ∼compile, console, and many other. For example, you can compile and run java from the command line:
>    java -cp ~/.sbt/boot/scala-2.12.12/lib/scala-library.jar:target/scala-2.12/classes/cp.lablessons.lesson1.HelloWorld
If multiple Apps exist, run a specific one with the command:
>    sbt> runMain cp.lablessons.lesson1.HelloWorld
9. Extend the build.sbt with dependencies that we may need during the semester.
>    resolvers ++= Seq(
>    "Sonatype OSS Snapshots" at
>    "https://oss.sonatype.org/content/repositories/snapshots",
>    "Sonatype OSS Releases" at
>    "https://oss.sonatype.org/content/repositories/releases",
>    "Typesafe Repository" at
>    "https://repo.typesafe.com/typesafe/releases/"
>    )
>    libraryDependencies ++= Seq(
>    "commons-io" % "commons-io" % "2.4"
>    ,"com.typesafe.akka" %% "akka-actor" % "2.8.5"
>    ,"com.typesafe.akka" %% "akka-remote" % "2.8.5"
>    )

If you are in the SBT shell and you modify the build.sbt you need to reload it:
    > sbt> reload

Note: If you want to use a more powerful IDE, you can use IntelliJ, start a new project from source files, and point to the built.sbt file. 
