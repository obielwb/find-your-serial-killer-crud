-- CreateTable
CREATE TABLE "SerialKillers" (
    "id" TEXT NOT NULL,
    "nome" TEXT NOT NULL,
    "armas" TEXT NOT NULL,
    "vezesContratado" INTEGER NOT NULL,
    "mortesConfirmadas" INTEGER NOT NULL,
    "precoPorContrato" DOUBLE PRECISION NOT NULL,
    "cep" INTEGER NOT NULL,
    "numero" INTEGER NOT NULL,
    "complemento" TEXT NOT NULL,

    CONSTRAINT "SerialKillers_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "SerialKillers_nome_key" ON "SerialKillers"("nome");
