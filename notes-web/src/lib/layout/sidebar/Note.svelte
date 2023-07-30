<script lang="ts">
    export let title: string
    export let active = false
</script>

<button on:click class="note-item" class:active>
    <h1>
        {title}
    </h1>
</button>

<style lang="postcss">
    .note-item {
        @apply text-start text-sm items-center px-6 py-3 w-full text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700;

        &.active {
            @apply bg-gray-200 dark:bg-gray-700 rounded-lg dark:text-white;
        }
    }
</style>
