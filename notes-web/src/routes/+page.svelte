<script>
    import Editor from '$lib/editor/Editor.svelte'
    import Sidebar from "$lib/layout/sidebar/Sidebar.svelte";
</script>

<div class="content-container">
    <Sidebar/>
    <Editor/>
</div>

<style lang="postcss">
    .content-container {
        /*overflow: auto;*/
        height: calc(100vh - 54px);
        @apply flex w-full;
    }
</style>