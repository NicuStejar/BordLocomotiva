# BordLocomotiva
O aplicatie care interfateaza Train Simulator cu un bord real de locomotiva. Poti sa conduci locomotive folosind controllere reale, fizice (similar cu volanul pentru pc).
Proiectul foloseste:
- un script .lua care se afla in directorul jocului si care scrie in timp real intr-un fisier (GetData.txt) parametrii locomotivei utilizate;
- un modul scris in Java care citeste (la un interval de timp dat) fisierul respectiv si scoate toate informatiile utile din el, informatii pe care le transmite mai departe;
- momentan, o parte din informatii sunt afisate intr-un JFrame, dar trebuie trimise prin USB catre un microcontroller ATMEL ce controleaza hardware-ul bordului fizic;
