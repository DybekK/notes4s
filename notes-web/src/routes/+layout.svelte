<script>
	import '../app.postcss'
	import Navbar from '$lib/layout/Navbar.svelte'
</script>

<div class="h-screen">
	<Navbar />
	<slot />
</div>
