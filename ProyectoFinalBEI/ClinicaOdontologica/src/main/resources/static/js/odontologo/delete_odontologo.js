function deleteBy(id){

    if(window.confirm("¿Estás seguro de que quieres eliminar el odontologo con id: " + id +"?")){
    //con fetch invocamos a la API de peliculas con el método DELETE
    //pasandole el id en la URL
    const url = '/odontologos/eliminar/'+ id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
    .then(response => response.json())

    //borrar la fila de la pelicula eliminada
    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
  }
}