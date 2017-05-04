### Testidokumentaatiota

Koska käyttöliittymää itsessään ei tarvitse testata, kokeilin kuitenkin varmistaa, että se toimii.

Käyttöliittymän tiedostovalikko (JFileChooser) estää valitsemasta tiedostoja väärässä muodossa - näiden tulee olla halutun tyyppisiä. Kuitenkin käytännössä tämä tarkoittaa vain sitä, että "mikä tahansa" tiedosto, jolla on oikea tiedostopääte menee läpi - esimerkiksi minkä tahansa tekstitiedoston pääte on mahdollista vaihtaa .vcf:ksi, jolloin näitäkin luetaan.

Tämä johtaa automaattisesti erroriin.

#### Writerit

Ohjelmassa on käytetty Javan omia writer-luokkia tulostiedoston kirjoittamiseen ja Readereita tulostiedostojen lukuun, mutaatiotestauksessa MutationAnalyser kärsii tästä (tuottaa 32% mutation coveragen). Kuitenkin testauksessa ei ole ikinä ilmennyt ongelmia writereiden/readereiden kanssa, jos erilaisten tiedostojen kanssa.

#### Väärässä formaatissa olevat tiedostot

Testatakseni, toimiiko ohjelma oikein tiedostojen ollessa vääränlaisia, muokkasin vääräntyyppisiä tiedostoja ja kokeilin ajaa ohjelmaa. Ohjelman tulisi lopettaa ajo ja kirjoittaa lokitiedostoon (logfile.txt), syy siihen, miksi ohjelma epäonnistuu. Kirjoitettu syy on aina ensimmäinen huomattu virhe. 
