Para rodar o código, é necessário instalar bibliotecas externas:
- `stb_image.h`: https://raw.githubusercontent.com/nothings/stb/master/stb_image.h
    - Deve estar na pasta raíz do projeto
- `Glad`: https://glad.dav1d.de/
    - Características:
        - Linguagem: C/C++
        - Specification: OpenGL
        - API/gl: 3.3
        - Profile: Core
        - Options: Check o Generate a loader
    - A pasta glad (com o glad.h) deve estar na pasta raíz
    - O arquivo glad.c deve estar fora da pasta glad, na raíz do projeto

- Por fim, só rodar `g++ jogo_pinguim.cpp glad.c -o jogo_pinguim -I. -lglfw -lGL -ldl && ./jogo_pinguim` em um terminal Linux.