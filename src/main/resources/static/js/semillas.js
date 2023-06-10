$(document).ready(function () {
  $('#tabla-semillas').DataTable({
      language: {
          processing: "Tratamiento en curso...",
          search: "Buscar&nbsp;:",
          lengthMenu: "Agrupar de a&nbsp; _MENU_ &nbsp;semillas",
          info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
          infoEmpty: "No existen datos.",
          infoFiltered: "(filtrado de _MAX_ elementos en total)",
          infoPostFix: "",
          loadingRecords: "Cargando...",
          zeroRecords: "No se encontraron datos con tu busqueda",
          emptyTable: "No hay datos disponibles en la tabla.",
          paginate: {
              first: "Primero",
              previous: "<",
              next: ">",
              last: "Ultimo"
          },
          aria: {
              sortAscending: ": active para ordenar la columna en orden ascendente",
              sortDescending: ": active para ordenar la columna en orden descendente"
          }
      },
      scrollY: 400,
      lengthMenu: [ [5, 10, -1], [5, 10, "All"] ],
      //lengthMenu: [ [20, 40, 60, 100, -1], [20, 40, 60, 100, "All"] ],
  });
});