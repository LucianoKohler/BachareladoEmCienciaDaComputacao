module Token where

-- Tipos passados no pdf do professor
type Cabecalho = [ String ]
type Registro = [ String ]
data CSV = CSV Cabecalho [ Registro ]
  deriving(Eq, Show)

data Token 
    = NEWL
    | VIRG
    | TEXT String
    deriving (Eq, Show)