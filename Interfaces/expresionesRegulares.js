function extraerHashtags(texto) {
  const regex = /#\w+/g;
  const hashtags = texto.match(regex);
  const lista = hashtags ? hashtags.map((tag) => tag.slice(1)) : [];
  const SetSinDuplicados = new Set(lista);
  return [...SetSinDuplicados];
}

console.log(
  extraerHashtags(
    "Aquí hay algunos hashtags: #javascript #coding #100DaysOfCode 100DaysOfCode"
  )
);

function extraerFlechas(texto) {
  const regex = /(\d{1,2}[<>-]{2,}\d{1,2})|([<>-]{2,})/g;
  const flechas = texto.match(regex);
  return flechas || [];
}

console.log(extraerFlechas("Mira estas flechas: <-- <---- --> --><--->"));

function extraerUrls(texto) {
  const regex = /https?:\/\/[^\s]+/g;
  const urls = texto.match(regex);
  return urls || [];
}

console.log(
  extraerUrls(
    "Visita https://www.ejemplo.com o http://test.com para más información."
  )
);
