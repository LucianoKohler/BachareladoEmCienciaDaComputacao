
class ObjetoSobrepostoException(Exception):
        def __init__(self, obj):
            print("Erro: as retas se sobrepõem")
            self.obj = obj