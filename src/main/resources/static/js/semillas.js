/* TABLE PAGINATION */
const d = document;

let $table = d.getElementById("tabla-semillas"),
$n = 40,
$rowCount = $table.rows.length,
$firstRow = $table.rows[0].firstElementChild.tagName,
$hasHead = ($firstRow === "TH"),
$tr = [],
$i,$ii,$j = ($hasHead)?1:0,
$th = ($hasHead?$table.rows[(0)].outerHTML:"");

let $pageCount = Math.ceil($rowCount / $n);
if ($pageCount > 1) {
	for ($i = $j,$ii = 0; $i < $rowCount; $i++, $ii++)
		$tr[$ii] = $table.rows[$i].outerHTML;
	$table.insertAdjacentHTML("afterend","<div id='pagination'></div");
	sort(1);
}

function sort($p) {
	let $rows = $th,$s = (($n * $p)-$n);
	for ($i = $s; $i < ($s+$n) && $i < $tr.length; $i++)
		$rows += $tr[$i];
	$table.innerHTML = $rows;
	d.getElementById("pagination").innerHTML = pageButtons($pageCount,$p);
	d.getElementById("id"+$p).setAttribute("class","active");
}

function pageButtons($pCount,$cur) {
	var	$prevDis = ($cur == 1)?"disabled":"",
		$nextDis = ($cur == $pCount)?"disabled":"",
		$pagination = "<input id='prev' type='button' value='🡨 Anterior' onclick='sort("+($cur - 1)+")' "+$prevDis+">";
	for ($i=1; $i<=$pCount;$i++)
		$pagination += "<input type='button' id='id"+$i+"'value='"+$i+"' onclick='sort("+$i+")'>";
	$pagination += "<input id='next' type='button' value='Siguiente 🡪' onclick='sort("+($cur + 1)+")' "+$nextDis+">";
	return $pagination;
}