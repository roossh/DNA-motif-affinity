### Ohjelman rakennekuvaus

Ohjelman käynnistyessä Main-luokka luo GUI-luokan. GUI-luokka kokoaa tarvittavat palaset yhteen. GUI:n kautta syötetään MatrixReaderiin Matrix, joka muokataan PWM:ksi PWMCalculatorin avulla. Kun uusi Matrix on luotu, MutationAnalyser käy läpi VCF:n, BED:n ja PWM:n. Ohjelma lukee tiedostoja ja palauttaa tulostiedoston, johon tämä laskee Matrix-luokan metodien avulla scoreja.

#### TO DO

Statistiikka tulee vielä viikonlopun aikana. Tämän tehtävänä on laskea hieman statistiikkaa käyttäjälle, kuten yleisimpiä mutaatiotyyppejä ja toteuttaa haluttaessa pienehkön permutaatiotestin.
