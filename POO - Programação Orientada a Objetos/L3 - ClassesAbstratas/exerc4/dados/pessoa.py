from abc import ABC, abstractmethod

class Pessoa(ABC): # ABC indica que é abstrata
    def __init__(self, nome):
        self.nome = nome

    @abstractmethod
    def __repr__(self):
        pass

