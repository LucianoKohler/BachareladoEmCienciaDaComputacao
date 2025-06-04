from dados.reta import Reta


class ObjetoSobrepostoException(Exception):
        def __init__(self, obj):
            print(f"Erro: {"Reta" if isinstance(obj, Reta) else "Ponto"} sobreposto(a) identificado")
            self.obj = obj