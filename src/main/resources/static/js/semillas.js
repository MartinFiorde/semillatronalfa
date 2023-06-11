$(document).ready(function () {
  $('#tabla-semillas').DataTable({
      language: {
          processing: "Tratamiento en curso...",
          search: "Buscar&nbsp;:",
          lengthMenu: "Mostrar&nbsp; _MENU_ &nbsp;semillas",
          info: "Semillas desde _START_ hasta _END_ de _TOTAL_",
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
      lengthMenu: [ [20, 40, 60, 100, -1], [20, 40, 60, 100, "todas"] ],
  });
});