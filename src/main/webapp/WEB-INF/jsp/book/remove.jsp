<h2>Czy chcesz usunac ksiazke ${book.title} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${book.id}">
    <button type="submit" value="yes" name="confirmed">YES</button>
    <button type="submit" value="no" name="confirmed">NO</button>
</form>