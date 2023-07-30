<script lang="ts">
    import {Button, Dropdown, DropdownDivider} from 'flowbite-svelte'
    import {editorStore} from "$lib/editor.store";

    const {editor} = editorStore

    const focus = () => $editor.chain().focus();
</script>

<Button color="light" size="xs" class="styles-button">Aa</Button>
<Dropdown class="flex flex-col w-40" triggeredBy=".styles-button">
    <button class="header-item title" on:click={() => focus().toggleHeading({level: 1}).run()}>Title</button>
    <button class="header-item header" on:click={() => focus().toggleHeading({level: 2}).run()}>Header</button>
    <button class="header-item subheader" on:click={() => focus().toggleHeading({level: 3}).run()}>Subheading</button>
    <button class="header-item paragraph" on:click={() => focus().setParagraph().run()}>Paragraph</button>
    <DropdownDivider/>
    <button class="header-item" on:click={() => focus().toggleBulletList().run()}>- Bullet list</button>
    <button class="header-item" on:click={() => focus().toggleOrderedList().run()}>1. Ordered list</button>
    <DropdownDivider/>
    <button class="header-item" on:click={() => focus().toggleCodeBlock().run()}>Code block</button>
</Dropdown>

<style lang="postcss">
    .header-item {
        @apply text-xs text-left px-4 py-1 w-full hover:bg-gray-100 dark:hover:bg-gray-600;

        &.title {
            @apply text-base font-extrabold;
        }

        &.header {
            @apply text-base font-bold;
        }

        &.subheader {
            @apply text-base font-semibold;
        }

        &.paragraph {
            @apply text-base;
        }
    }
</style>